package com.acme.bank.loan.domain.converter;

import com.acme.bank.loan.domain.entity.RegisterLoanEntity;
import com.acme.bank.loan.domain.model.RegisterLoanModel;
import org.springframework.stereotype.Component;

@Component
public class RegisterLoanEntityToModelConverter extends AbstractConverter<RegisterLoanEntity, RegisterLoanModel> {

    @Override
    public RegisterLoanModel convert(RegisterLoanEntity entity) {
        RegisterLoanModel model = new RegisterLoanModel();
        model.setEventId(entity.getEventId());
        model.setPersonId(entity.getPersonId());
        model.setRegisteredTimestamp(entity.getRegisteredTimestamp());
        model.setEntitledTimestamp(entity.getEntitledTimestamp());
        model.setRejectedTimestamp(entity.getRejectedTimestamp());
        model.setAmount(entity.getAmount());
        return model;
    }
}
