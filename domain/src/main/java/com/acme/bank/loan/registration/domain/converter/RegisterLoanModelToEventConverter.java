package com.acme.bank.loan.registration.domain.converter;

import java.time.ZonedDateTime;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.acme.bank.loan.registration.domain.event.RegisterLoanEvent;
import com.acme.bank.loan.registration.domain.model.RegisterLoanModel;

@Component
public class RegisterLoanModelToEventConverter extends AbstractConverter<RegisterLoanModel, RegisterLoanEvent> {

    @Override
    public RegisterLoanEvent convert(RegisterLoanModel model) {
        RegisterLoanEvent event = new RegisterLoanEvent();
        event.setUuid(UUID.randomUUID());
        event.setPersonalId(model.getPersonalId());
        event.setRegisteredTimestamp(ZonedDateTime.now());
        event.setAmount(model.getAmount());
        return event;
    }
}
