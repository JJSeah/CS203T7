package com.example.electric.exception;
import java.time.LocalTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class CannotCreateBookingException extends RuntimeException {
    private LocalTime timeOfNextAppointment;

    public CannotCreateBookingException(LocalTime timeOfNextAppointment) {
        super("Cannot create a booking because there is an upcoming appointment.");
        this.timeOfNextAppointment = timeOfNextAppointment;
    }

    public ResponseEntity<String> toResponseEntity() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(getMessage() + ", Time of Next Appointment: " + timeOfNextAppointment);
    }
}
