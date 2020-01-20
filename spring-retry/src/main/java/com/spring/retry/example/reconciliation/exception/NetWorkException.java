package com.spring.retry.example.reconciliation.exception;


public class NetWorkException extends RuntimeException {

    public NetWorkException() {
    }

    public NetWorkException(String message) {
        super(message);
    }
}
