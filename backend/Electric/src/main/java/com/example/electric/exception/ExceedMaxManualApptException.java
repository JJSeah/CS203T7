package com.example.electric.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ExceedMaxManualApptException extends Exception {
    private int currentAppointment;
    private int maxAllowed;

    public ExceedMaxManualApptException(int currentAppointment, int maxAllowed) {
        super("Cannot exceed maximum manual appointment limit. Current manual appointment: " + currentAppointment
                + ", Maximum allowed: " + maxAllowed);
        this.currentAppointment = currentAppointment;
        this.maxAllowed = maxAllowed;
    }

    public int getCurrentAppointment() {
        return currentAppointment;
    }

    public int getMaxAllowed() {
        return maxAllowed;
    }

    public ResponseEntity<String> toResponseEntity() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Cannot exceed maximum manual appointment limit. Current manual appointment: "
                        + currentAppointment + ", Maximum allowed: " + maxAllowed);
    }
}
