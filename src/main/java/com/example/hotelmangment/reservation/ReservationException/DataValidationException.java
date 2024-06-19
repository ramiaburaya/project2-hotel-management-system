package com.example.hotelmangment.reservation.ReservationException;

public class DataValidationException extends RuntimeException {
    public DataValidationException(String message) {
        super(message);
    }
}