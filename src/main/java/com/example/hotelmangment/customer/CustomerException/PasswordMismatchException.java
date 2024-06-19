package com.example.hotelmangment.customer.CustomerException;

public class PasswordMismatchException extends RuntimeException {
    public PasswordMismatchException() {
        super("current password is incorrect");
    }
}
