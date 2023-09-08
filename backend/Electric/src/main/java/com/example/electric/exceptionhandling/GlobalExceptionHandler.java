package com.example.electric.exceptionhandling;

import com.example.electric.dto.ErrorResponse;
import com.example.electric.exception.ObjectNotFoundException;
import com.example.electric.exception.ObjectAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ObjectAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleObjectAlreadyExists(ObjectAlreadyExistsException ex) {
        ErrorResponse response = new ErrorResponse(ex.getErrorCode(),ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAppointmentNotFound(ObjectNotFoundException e) {
        ErrorResponse response = new ErrorResponse(e.getErrorCode(),e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}