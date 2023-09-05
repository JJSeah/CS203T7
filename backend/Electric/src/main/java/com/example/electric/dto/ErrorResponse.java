package com.example.electric.dto;

import com.example.electric.error.ErrorCode;

public class ErrorResponse {
    private final String errorCode;
    private final String message;

    public ErrorResponse(ErrorCode errorCode, String message) {
        this.errorCode = errorCode.name();
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }
}
