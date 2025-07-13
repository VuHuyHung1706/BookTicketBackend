package com.web.backend.constant;

public enum InvoiceStatus {
    CREATED("CREATED"),         // Created
    PAID("PAID"),              // Paid
    CANCELLED("CANCELLED");     // Cancelled

    private final String value;

    InvoiceStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
