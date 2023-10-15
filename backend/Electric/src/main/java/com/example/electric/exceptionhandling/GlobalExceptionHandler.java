package com.example.electric.exceptionhandling;

import com.example.electric.dto.ErrorResponse;
import com.example.electric.exception.*;

import jakarta.servlet.http.HttpServletResponse;

import com.example.electric.exception.ExceedMaxManualApptException;
import com.example.electric.exception.ForbiddenException;
import com.example.electric.exception.ObjectAlreadyExistsException;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.*;

import org.springframework.http.*;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

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

    
    //when arguments did not match the type. e.g user id not a number
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public void handleTypeMismatch(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }

    //method that handle failed validation
    protected ResponseEntity<Object>
    handleMethodArgumentNotValid(MethodArgumentNotValidException ex, 
                                HttpHeaders headers,
                                 HttpStatus status, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        String message = "";
        for (ObjectError objectError : ex.getBindingResult().getAllErrors()){
            message = message + objectError.getDefaultMessage();
        }
        body.put("message", message);
        body.put("path", request.getDescription(false));
        return new ResponseEntity<>(body, headers, status);

    }

    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(AccessDeniedException ex) {
        // You can log or print the error message here
        System.err.println("Access Denied: " + ex.getMessage());

        // You can redirect or return an error page
        // Example: return "error/access-denied";
        
        // Alternatively, you can throw a custom exception or return a custom response.
        return "error/access-denied"; // This is just an example, you can customize it as needed.
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<String> handleForbiddenException(ForbiddenException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }

    
    @ExceptionHandler(ExceedMaxManualApptException.class)
    public ResponseEntity<String> handleMaxManualException(ExceedMaxManualApptException e) {
        return e.toResponseEntity();
    }

    @ExceptionHandler(CanCreateBookingException.class)
        public ResponseEntity<String> handleCanCreateBookingException(CanCreateBookingException e) {
        return e.toResponseEntity();
    }

        @ExceptionHandler(CannotCreateBookingException.class)
        public ResponseEntity<String> handleCannotCreateBookingException(CannotCreateBookingException e) {
        return e.toResponseEntity();
    }
}

