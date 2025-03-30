package com.hrd.homework003.exception;

public class UserNotFoundExceptionHandler extends RuntimeException {
    public UserNotFoundExceptionHandler(String message) {
        super(message);
    }
}
