package com.acme.bank.loan.domain.entity;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.UUID;

@Table(name = "REGISTER_LOAN")
@Entity
public class RegisterLoanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;
    private String personalId;
    private double amount;
    private ZonedDateTime registeredTimestamp;
    private ZonedDateTime entitledTimestamp;
    private ZonedDateTime rejectedTimestamp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }

    public ZonedDateTime getRegisteredTimestamp() {
        return registeredTimestamp;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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
