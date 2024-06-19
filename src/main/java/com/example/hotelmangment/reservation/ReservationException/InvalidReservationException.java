package com.example.hotelmangment.reservation.ReservationException;

public class InvalidReservationException extends RuntimeException {
    public InvalidReservationException(String message) {
        super(message);
    }
}