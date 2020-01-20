package com.spring.retry.example.reconciliation.exception;


public class ValidateException extends RuntimeException {

    public ValidateException() {
    }

    public ValidateException(String message) {
        super(message);
    }
}
