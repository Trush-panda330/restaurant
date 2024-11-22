package com.example.domain.exception.customer;

public class CustomerUpdateException extends RuntimeException {
    public CustomerUpdateException(String message) {
        super(message);
    }
}
