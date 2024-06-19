package com.example.hotelmangment.customer.CustomerException;

public class PasswordValidation extends RuntimeException{
    public PasswordValidation(String message){
        super(message);
    }
}
