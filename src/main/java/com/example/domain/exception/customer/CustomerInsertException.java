package com.example.domain.exception.customer;

public class CustomerInsertException extends RuntimeException {
    public CustomerInsertException(String message) {
        super(message);
    }
}
