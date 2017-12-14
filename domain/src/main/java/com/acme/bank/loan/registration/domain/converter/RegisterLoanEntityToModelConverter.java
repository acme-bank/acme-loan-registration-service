package com.acme.bank.loan.registration.domain.converter;

import org.springframework.stereotype.Component;

import com.acme.bank.loan.registration.domain.entity.RegisterLoanEntity;
import com.acme.bank.loan.registration.domain.model.RegisterLoanModel;

@Component
public class RegisterLoanEntityToModelConverter extends AbstractConverter<RegisterLoanEntity, RegisterLoanModel> {

    @Override
    public RegisterLoanModel convert(RegisterLoanEntity entity) {
        RegisterLoanModel model = new RegisterLoanModel();
        model.setUuid(entity.getUuid());
        model.setPersonalId(entity.getPersonalId());
        model.setRegisteredTimestamp(entity.getRegisteredTimestamp());
        model.setEntitledTimestamp(entity.getEntitledTimestamp());
        model.setRejectedTimestamp(entity.getRejectedTimestamp());
        model.setAmount(entity.getAmount());
        return model;
    }
}
