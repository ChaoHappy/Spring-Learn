package com.spring.retry.example.reconciliation.exception;


public class FileException extends RuntimeException {

    public FileException() {
    }

    public FileException(String message) {
        super(message);
    }
}
