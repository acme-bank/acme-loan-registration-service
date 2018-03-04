package com.acme.bank.loan.domain.converter;

import com.acme.bank.loan.domain.event.RegisterLoanEvent;
import com.acme.bank.loan.domain.model.RegisterLoanModel;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.UUID;

@Component
public class RegisterLoanModelToEventConverter extends AbstractConverter<RegisterLoanModel, RegisterLoanEvent> {

    @Override
    public RegisterLoanEvent convert(RegisterLoanModel model) {
        RegisterLoanEvent event = new RegisterLoanEvent();
        event.setEventId(UUID.randomUUID());
        event.setPersonId(model.getPersonId());
        event.setRegisteredTimestamp(ZonedDateTime.now());
        event.setAmount(model.getAmount());
        return event;
    }
}
