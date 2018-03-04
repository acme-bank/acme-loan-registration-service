package com.acme.bank.loan.domain.converter;

import com.acme.bank.loan.domain.entity.RegisterLoanEntity;
import com.acme.bank.loan.domain.event.RegisterLoanEvent;
import org.springframework.stereotype.Component;

@Component
public class RegisterLoanEventToEntityConverter extends AbstractConverter<RegisterLoanEvent, RegisterLoanEntity> {

    @Override
    public RegisterLoanEntity convert(RegisterLoanEvent event) {
        RegisterLoanEntity entity = new RegisterLoanEntity();
        entity.setEventId(event.getEventId());
        entity.setPersonId(event.getPersonId());
        entity.setRegisteredTimestamp(event.getRegisteredTimestamp());
        entity.setAmount(event.getAmount());
        return entity;
    }
}
