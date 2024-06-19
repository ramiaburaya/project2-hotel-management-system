package com.example.hotelmangment.reservation;

import com.example.hotelmangment.reservation.Model.Reservation;
import com.example.hotelmangment.reservation.Model.ReservationStatus;
import com.example.hotelmangment.reservation.Service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CUSTOMER')")
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        Reservation createdReservation = reservationService.createReservation(reservation);
        return ResponseEntity.ok(createdReservation);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CUSTOMER')")
    public ResponseEntity<Reservation> updateReservation(@PathVariable Long id, @RequestBody Reservation reservation) {
        Reservation updatedReservation = reservationService.updateReservation(id, reservation);
        return ResponseEntity.ok(updatedReservation);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CUSTOMER')")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Long id) {
        Reservation reservation = reservationService.getReservationById(id);
        return ResponseEntity.ok(reservation);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> reservations = reservationService.getAllReservations();
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/customer/{customerId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CUSTOMER')")
    public ResponseEntity<List<Reservation>> getReservationsByCustomerId(@PathVariable Long customerId) {
        List<Reservation> reservations = reservationService.getReservationsByCustomerId(customerId);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/room/{roomId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<List<Reservation>> getReservationsByRoomId(@PathVariable Long roomId) {
        List<Reservation> reservations = reservationService.getReservationsByRoomId(roomId);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/status/{status}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Reservation>> getReservationsByStatus(@PathVariable ReservationStatus status) {
        List<Reservation> reservations = reservationService.getReservationsByStatus(status);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/checkin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Reservation>> getReservationsByCheckInDateRange(@RequestParam Date startDate, @RequestParam Date endDate) {
        List<Reservation> reservations = reservationService.getReservationsByCheckInDateRange(startDate, endDate);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/checkout")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Reservation>> getReservationsByCheckOutDateRange(@RequestParam Date startDate, @RequestParam Date endDate) {
        List<Reservation> reservations = reservationService.getReservationsByCheckOutDateRange(startDate, endDate);
        return ResponseEntity.ok(reservations);
    }
}
