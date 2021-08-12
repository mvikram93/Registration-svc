package com.alchemy.registration.exceptions;

public class UserExistException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private static String message;

    public UserExistException(String message) {
        super(message);
    }
}
