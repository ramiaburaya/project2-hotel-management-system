package com.example.hotelmangment.customer.CustomerException;


public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException() {
        super("Customer Not Found");
    }
}
