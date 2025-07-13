package com.web.backend.constant;

public enum TicketStatus {
    BOOKED("BOOKED"),           // Booked
    CANCELLED("CANCELLED"),     // Cancelled
    PAID("PAID");              // Paid

    private final String value;

    TicketStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
