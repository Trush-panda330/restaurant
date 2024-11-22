package com.example.domain.exception.customer;

public class CustomerDeleteException extends RuntimeException {
    public CustomerDeleteException(String message) {
        super(message);
    }
}
