package com.acme.bank.loan.registration.service.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.acme.bank.loan.registration.domain.entity.RegisterLoanEntity;
import com.acme.bank.loan.registration.domain.event.EntitleLoanEvent;
import com.acme.bank.loan.registration.domain.event.RegisterLoanEvent;
import com.acme.bank.loan.registration.domain.event.RejectLoanEvent;
import com.acme.bank.loan.registration.domain.model.RegisterLoanModel;
import com.acme.bank.loan.registration.service.producer.RegisterLoanKafkaProducer;
import com.acme.bank.loan.registration.service.repository.RegisterLoanRepository;

@SuppressWarnings({"UnusedReturnValue"})
@Service
public class RegisterLoanService {

    private final RegisterLoanRepository registerLoanRepository;
    private final RegisterLoanKafkaProducer registerLoanKafkaProducer;
    private final ConversionService conversionService;

    @Autowired
    public RegisterLoanService(final RegisterLoanRepository registerLoanRepository,
                               final RegisterLoanKafkaProducer registerLoanKafkaProducer,
                               final ConversionService conversionService) {
        this.registerLoanRepository = registerLoanRepository;
        this.registerLoanKafkaProducer = registerLoanKafkaProducer;
        this.conversionService = conversionService;
    }

    public List<RegisterLoanModel> find() {
        List<RegisterLoanEntity> entities = registerLoanRepository.findAll();
        return entities.stream()
                .map(entity -> conversionService.convert(entity, RegisterLoanModel.class))
                .collect(Collectors.toList());
    }

    public List<RegisterLoanModel> find(String personalId) {
        Assert.notNull(personalId, "Personal ID is null");

        List<RegisterLoanEntity> entities = registerLoanRepository.findAllByPersonalId(personalId);
        return entities.stream()
                .map(entity -> conversionService.convert(entity, RegisterLoanModel.class))
                .collect(Collectors.toList());
    }

    public RegisterLoanModel get(UUID uuid) {
        Assert.notNull(uuid, "UUID is null");

        RegisterLoanEntity entity = registerLoanRepository.findByUuid(uuid);
        return conversionService.convert(entity, RegisterLoanModel.class);
    }

    @Transactional
    public UUID save(RegisterLoanModel model) {
        Assert.notNull(model, "Model is null");

        RegisterLoanEvent event = conversionService.convert(model, RegisterLoanEvent.class);
        Assert.notNull(event, "Event is null");

        RegisterLoanEntity entity = conversionService.convert(event, RegisterLoanEntity.class);
        Assert.notNull(entity, "Entity is null");

        registerLoanRepository.save(entity);
        registerLoanKafkaProducer.send(event);

        return event.getUuid();
    }

    @Transactional
    public UUID save(EntitleLoanEvent event) {
        return Optional.ofNullable(event)
                .map(EntitleLoanEvent::getUuid)
                .map(registerLoanRepository::findByUuid)
                .map(entity -> {
                    entity.setEntitledTimestamp(event.getEntitledTimestamp());
                    return entity;
                })
                .map(registerLoanRepository::save)
                .map(RegisterLoanEntity::getUuid)
                .orElse(null);
    }

    @Transactional
    public UUID save(RejectLoanEvent event) {
        return Optional.ofNullable(event)
                .map(RejectLoanEvent::getUuid)
                .map(registerLoanRepository::findByUuid)
                .map(entity -> {
                    entity.setRejectedTimestamp(event.getRejectedTimestamp());
                    return entity;
                })
                .map(registerLoanRepository::save)
                .map(RegisterLoanEntity::getUuid)
                .orElse(null);
    }
}
