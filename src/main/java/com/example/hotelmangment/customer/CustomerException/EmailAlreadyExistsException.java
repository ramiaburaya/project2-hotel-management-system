package com.example.hotelmangment.customer.CustomerException;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException() {
        super("Email already exists");
    }
}