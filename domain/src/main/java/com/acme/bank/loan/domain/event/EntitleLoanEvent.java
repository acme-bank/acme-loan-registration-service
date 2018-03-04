package com.acme.bank.loan.domain.event;

import java.time.ZonedDateTime;
import java.util.UUID;

public class EntitleLoanEvent {

    private UUID eventId;
    private ZonedDateTime entitledTimestamp;
    private String details;

    public UUID getEventId() {
        return eventId;
    }

    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }

    public ZonedDateTime getEntitledTimestamp() {
        return entitledTimestamp;
    }

    public void setEntitledTimestamp(ZonedDateTime entitledTimestamp) {
        this.entitledTimestamp = entitledTimestamp;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
