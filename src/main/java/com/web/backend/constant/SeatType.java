package com.web.backend.constant;

public enum SeatType {
    STANDARD("STANDARD"),
    VIP("VIP"),
    COUPLE("COUPLE");

    private final String value;

    SeatType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
