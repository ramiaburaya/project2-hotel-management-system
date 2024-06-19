package com.example.hotelmangment.customer.CustomerException;

public class PhoneNumberAlreadyExistsException extends RuntimeException {
    public PhoneNumberAlreadyExistsException() {
        super("Phone number already exists");
    }
}
