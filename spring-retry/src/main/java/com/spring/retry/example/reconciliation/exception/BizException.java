package com.spring.retry.example.reconciliation.exception;


public class BizException extends RuntimeException {

    public BizException() {
    }

    public BizException(String message) {
        super(message);
    }
}
