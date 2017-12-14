package com.acme.bank.loan.registration.domain.converter;

import org.springframework.stereotype.Component;

import com.acme.bank.loan.registration.domain.entity.RegisterLoanEntity;
import com.acme.bank.loan.registration.domain.event.RegisterLoanEvent;

@Component
public class RegisterLoanEventToEntityConverter extends AbstractConverter<RegisterLoanEvent, RegisterLoanEntity> {

    @Override
    public RegisterLoanEntity convert(RegisterLoanEvent event) {
        RegisterLoanEntity entity = new RegisterLoanEntity();
        entity.setUuid(event.getUuid());
        entity.setPersonalId(event.getPersonalId());
        entity.setRegisteredTimestamp(event.getRegisteredTimestamp());
        entity.setAmount(event.getAmount());
        return entity;
    }
}
