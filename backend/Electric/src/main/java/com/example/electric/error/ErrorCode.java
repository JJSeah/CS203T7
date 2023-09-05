package com.example.electric.error;

public enum ErrorCode {
    E1001("Database error"),
    E1002("Data not found"),
    E2001("Validation failed"),
    E3001("Authentication failed");

    private final String description;

    ErrorCode(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
