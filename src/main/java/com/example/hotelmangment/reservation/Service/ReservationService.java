package com.example.hotelmangment.reservation.Service;

import com.example.hotelmangment.reservation.Model.Reservation;
import com.example.hotelmangment.reservation.Model.ReservationStatus;

import java.util.Date;
import java.util.List;

public interface ReservationService {
    Reservation createReservation(Reservation reservation);
    Reservation updateReservation(Long id, Reservation reservation);
    void deleteReservation(Long id);
    Reservation getReservationById(Long id);
    List<Reservation> getAllReservations();
    List<Reservation> getReservationsByCustomerId(Long customerId);
    List<Reservation> getReservationsByRoomId(Long roomId);
    List<Reservation> getReservationsByStatus(ReservationStatus status);
    List<Reservation> getReservationsByCheckInDateRange(Date startDate, Date endDate);
    List<Reservation> getReservationsByCheckOutDateRange(Date startDate, Date endDate);
}
