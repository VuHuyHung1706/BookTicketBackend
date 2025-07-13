package com.web.backend.constant;

public enum MovieStatus {
    COMING_SOON("COMING_SOON"),
    NOW_SHOWING("NOW_SHOWING"),
    ENDED("ENDED");

    private final String value;

    MovieStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
