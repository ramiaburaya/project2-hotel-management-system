package com.example.hotelmangment.reservation.Service;

import com.example.hotelmangment.reservation.Model.Reservation;
import com.example.hotelmangment.reservation.Model.ReservationRepository;
import com.example.hotelmangment.reservation.Model.ReservationStatus;
import com.example.hotelmangment.reservation.ReservationException.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    @Override
    public Reservation createReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation updateReservation(Long id, Reservation reservation) {
        Optional<Reservation> existingReservation = reservationRepository.findById(id);
        if (existingReservation.isPresent()) {
            Reservation updatedReservation = existingReservation.get();
            updatedReservation.setCheckInDate(reservation.getCheckInDate());
            updatedReservation.setCheckOutDate(reservation.getCheckOutDate());
            updatedReservation.setStatus(reservation.getStatus());
            updatedReservation.setCustomer(reservation.getCustomer());
            updatedReservation.setRoom(reservation.getRoom());
            return reservationRepository.save(updatedReservation);
        } else {
            throw new ResourceNotFoundException("Reservation not found with id " + id);
        }
    }

    @Override
    public void deleteReservation(Long id) {
        if (reservationRepository.existsById(id)) {
            reservationRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Reservation not found with id " + id);
        }
    }

    @Override
    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Reservation not found with id " + id));
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public List<Reservation> getReservationsByCustomerId(Long customerId) {
        return reservationRepository.findByCustomerId(customerId);
    }

    @Override
    public List<Reservation> getReservationsByRoomId(Long roomId) {
        return reservationRepository.findByRoomId(roomId);
    }

    @Override
    public List<Reservation> getReservationsByStatus(ReservationStatus status) {
        return reservationRepository.findByStatus(status);
    }

    @Override
    public List<Reservation> getReservationsByCheckInDateRange(Date startDate, Date endDate) {
        return reservationRepository.findByCheckInDateBetween(startDate, endDate);
    }

    @Override
    public List<Reservation> getReservationsByCheckOutDateRange(Date startDate, Date endDate) {
        return reservationRepository.findByCheckOutDateBetween(startDate, endDate);
    }
}
