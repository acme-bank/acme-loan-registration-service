package com.acme.bank.loan.domain.event;

import java.time.ZonedDateTime;
import java.util.UUID;

public class RejectLoanEvent {

    private UUID eventId;
    private ZonedDateTime rejectedTimestamp;
    private String details;

    public UUID getEventId() {
        return eventId;
    }

    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }

    public ZonedDateTime getRejectedTimestamp() {
        return rejectedTimestamp;
    }

    public void setRejectedTimestamp(ZonedDateTime rejectedTimestamp) {
        this.rejectedTimestamp = rejectedTimestamp;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
