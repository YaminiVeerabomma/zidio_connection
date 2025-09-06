package com.example.exception;

public class UserPaymentStatusNotFoundException extends RuntimeException {
    public UserPaymentStatusNotFoundException(String message) {
        super(message);
    }
}
