package com.example.electric.exception;

import java.time.LocalTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CanCreateBookingException extends RuntimeException {
    private LocalTime timeOfNextAppointment;

    public CanCreateBookingException(LocalTime timeOfNextAppointment) {
        super("Can Create a booking. However, need to leave before the the next appointment");
        this.timeOfNextAppointment = timeOfNextAppointment;
    }

    public CanCreateBookingException(){
        super("There is no upcoming appointments in at least the next hour, feel free to book an appointment");
    }

    public ResponseEntity<String> toResponseEntity() {
        if(timeOfNextAppointment != null){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(getMessage() + ", Time of Next Appointment: " + timeOfNextAppointment);
        }else {
        return ResponseEntity.status(HttpStatus.OK)
            .body(getMessage());
        }
    }
}
