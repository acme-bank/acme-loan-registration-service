package com.acme.bank.loan.domain.converter;

import org.springframework.stereotype.Component;

import com.acme.bank.loan.domain.entity.RegisterLoanEntity;
import com.acme.bank.loan.domain.event.RegisterLoanEvent;

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
