package com.example.electric.exception;

import com.example.electric.error.ErrorCode;

public class ObjectNotFoundException extends RuntimeException {
    private final ErrorCode errorCode;

    public ObjectNotFoundException(ErrorCode errorCode) {
        super(errorCode.getDescription());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
