package com.hexaware.restapi.exception;

public class CancellationNotFoundException extends RuntimeException {
    public CancellationNotFoundException(String message) {
        super(message);
    }
}
