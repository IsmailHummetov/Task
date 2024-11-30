package com.example.transaction.exception;

public class OverAmountException extends RuntimeException {
    public OverAmountException(String message) {
        super(message);
    }
}
