package com.example.electric.exception;

import com.example.electric.error.ErrorCode;

public class AppointmentNotFoundException extends RuntimeException {
    private final ErrorCode errorCode;

    public AppointmentNotFoundException(ErrorCode errorCode) {
        super(errorCode.getDescription());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
