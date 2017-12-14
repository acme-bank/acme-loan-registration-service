package com.acme.bank.loan.registration.domain.converter;

import org.springframework.stereotype.Component;

import com.acme.bank.loan.registration.domain.entity.RegisterLoanEntity;
import com.acme.bank.loan.registration.domain.event.RegisterLoanEvent;

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
