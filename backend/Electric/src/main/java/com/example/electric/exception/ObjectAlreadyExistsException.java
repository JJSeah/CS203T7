package com.example.electric.exception;

import com.example.electric.error.ErrorCode;

public class ObjectAlreadyExistsException extends RuntimeException{
    private final ErrorCode errorCode;

    public ObjectAlreadyExistsException(ErrorCode errorCode) {
        super(errorCode.getDescription());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
