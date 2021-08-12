package com.alchemy.registration.exceptions;

import org.springframework.http.HttpStatus;

import java.time.LocalDate;

class UserExceptionHandler {
    private LocalDate timestamp;
    private HttpStatus errorCode;
    private String errorMessage;



    public UserExceptionHandler(String errorMessage, HttpStatus errorCode, LocalDate timestamp) {
        this.timestamp = timestamp;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;

    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public HttpStatus getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
