package com.acme.bank.loan.domain.model;

import java.time.ZonedDateTime;
import java.util.UUID;

public class RegisterLoanModel {

    private UUID eventId;
    private UUID personId;
    private double amount;
    private ZonedDateTime registeredTimestamp;
    private ZonedDateTime entitledTimestamp;
    private ZonedDateTime rejectedTimestamp;

    public UUID getEventId() {
        return eventId;
    }

    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }

    public UUID getPersonId() {
        return personId;
    }

    public void setPersonId(UUID personId) {
        this.personId = personId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public ZonedDateTime getRegisteredTimestamp() {
        return registeredTimestamp;
    }

    public void setRegisteredTimestamp(ZonedDateTime registeredTimestamp) {
        this.registeredTimestamp = registeredTimestamp;
    }

    public ZonedDateTime getEntitledTimestamp() {
        return entitledTimestamp;
    }

    public void setEntitledTimestamp(ZonedDateTime entitledTimestamp) {
        this.entitledTimestamp = entitledTimestamp;
    }

    public ZonedDateTime getRejectedTimestamp() {
        return rejectedTimestamp;
    }

    public void setRejectedTimestamp(ZonedDateTime rejectedTimestamp) {
        this.rejectedTimestamp = rejectedTimestamp;
    }
}
