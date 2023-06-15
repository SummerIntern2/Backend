package com.example.backend.exceptions;

public class UserDoesNotExistException extends RuntimeException{
    private String message;
    
    public UserDoesNotExistException() {
    }

    public UserDoesNotExistException(String message) {
        super(message);
        this.message = message;
    }
}
