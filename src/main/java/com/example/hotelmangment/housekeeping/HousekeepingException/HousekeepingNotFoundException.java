package com.example.hotelmangment.housekeeping.HousekeepingException;

public class HousekeepingNotFoundException extends RuntimeException {
    public HousekeepingNotFoundException() {
        super("Housekeeping task not found");
    }
}
