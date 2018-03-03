package com.acme.bank.loan.domain.converter;

import org.springframework.stereotype.Component;

import com.acme.bank.loan.domain.entity.RegisterLoanEntity;
import com.acme.bank.loan.domain.event.RegisterLoanEvent;

@Component
public class RegisterLoanEntityToEventConverter extends AbstractConverter<RegisterLoanEntity, RegisterLoanEvent> {

    @Override
    public RegisterLoanEvent convert(RegisterLoanEntity entity) {
        RegisterLoanEvent event = new RegisterLoanEvent();
        event.setUuid(entity.getUuid());
        event.setPersonalId(entity.getPersonalId());
        event.setRegisteredTimestamp(entity.getRegisteredTimestamp());
        event.setAmount(entity.getAmount());
        return event;
    }
}
