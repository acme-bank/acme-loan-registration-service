package com.acme.bank.loan.domain.converter;

import com.acme.bank.loan.domain.entity.RegisterLoanEntity;
import com.acme.bank.loan.domain.event.RegisterLoanEvent;
import org.springframework.stereotype.Component;

@Component
public class RegisterLoanEntityToEventConverter extends AbstractConverter<RegisterLoanEntity, RegisterLoanEvent> {

    @Override
    public RegisterLoanEvent convert(RegisterLoanEntity entity) {
        RegisterLoanEvent event = new RegisterLoanEvent();
        event.setEventId(entity.getEventId());
        event.setPersonId(entity.getPersonId());
        event.setRegisteredTimestamp(entity.getRegisteredTimestamp());
        event.setAmount(entity.getAmount());
        return event;
    }
}
