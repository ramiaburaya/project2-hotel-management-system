package com.example.hotelmangment.reservation.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByCustomerId(Long customerId);
    List<Reservation> findByRoomId(Long roomId);
    List<Reservation> findByStatus(ReservationStatus status);
    List<Reservation> findByCheckInDateBetween(Date startDate, Date endDate);
    List<Reservation> findByCheckOutDateBetween(Date startDate, Date endDate);
}
