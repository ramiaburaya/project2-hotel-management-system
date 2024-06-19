package com.example.hotelmangment.reservation;

import com.example.hotelmangment.reservation.Model.Reservation;
import com.example.hotelmangment.reservation.Model.ReservationStatus;
import com.example.hotelmangment.reservation.Service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/reservation")
@Tag(name = "Reservation", description = "API for reservation management")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Operation(summary = "Create a new reservation", description = "Create a new reservation. Accessible by admin and customers.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Reservation.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized access", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CUSTOMER')")
    public ResponseEntity<Reservation> createReservation(
            @Parameter(description = "Reservation object to be created", required = true) @RequestBody  Reservation reservation) {
        Reservation createdReservation = reservationService.createReservation(reservation);
        return ResponseEntity.ok(createdReservation);
    }

    @Operation(summary = "Update an existing reservation", description = "Update an existing reservation. Accessible by admin and customers.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Reservation.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized access", content = @Content),
            @ApiResponse(responseCode = "404", description = "Reservation not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CUSTOMER')")
    public ResponseEntity<Reservation> updateReservation(
            @Parameter(description = "ID of the reservation to be updated", required = true) @PathVariable Long id,
            @Parameter(description = "Updated reservation object", required = true) @RequestBody @Valid Reservation reservation) {
        Reservation updatedReservation = reservationService.updateReservation(id, reservation);
        return ResponseEntity.ok(updatedReservation);
    }

    @Operation(summary = "Delete a reservation", description = "Delete a reservation. Only accessible by admins.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Reservation deleted successfully", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized access", content = @Content),
            @ApiResponse(responseCode = "404", description = "Reservation not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteReservation(
            @Parameter(description = "ID of the reservation to be deleted", required = true) @PathVariable Long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get reservation by ID", description = "Retrieve a reservation by its ID. Accessible by admin and customers.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Reservation.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized access", content = @Content),
            @ApiResponse(responseCode = "404", description = "Reservation not found", content = @Content)
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CUSTOMER')")
    public ResponseEntity<Reservation> getReservationById(
            @Parameter(description = "ID of the reservation to be retrieved", required = true) @PathVariable Long id) {
        Reservation reservation = reservationService.getReservationById(id);
        return ResponseEntity.ok(reservation);
    }

    @Operation(summary = "Get all reservations", description = "Retrieve a list of all reservations. Only accessible by admins.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of reservations retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Reservation.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized access", content = @Content)
    })
    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> reservations = reservationService.getAllReservations();
        return ResponseEntity.ok(reservations);
    }

    @Operation(summary = "Get reservations by customer ID", description = "Retrieve reservations by customer ID. Accessible by admin and customers.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of reservations retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Reservation.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized access", content = @Content)
    })
    @GetMapping("/customer/{customerId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CUSTOMER')")
    public ResponseEntity<List<Reservation>> getReservationsByCustomerId(
            @Parameter(description = "ID of the customer whose reservations are to be retrieved", required = true) @PathVariable Long customerId) {
        List<Reservation> reservations = reservationService.getReservationsByCustomerId(customerId);
        return ResponseEntity.ok(reservations);
    }

    @Operation(summary = "Get reservations by room ID", description = "Retrieve reservations by room ID. Accessible by admin and customers.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of reservations retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Reservation.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized access", content = @Content)
    })
    @GetMapping("/room/{roomId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Reservation>> getReservationsByRoomId(
            @Parameter(description = "ID of the room whose reservations are to be retrieved", required = true) @PathVariable Long roomId) {
        List<Reservation> reservations = reservationService.getReservationsByRoomId(roomId);
        return ResponseEntity.ok(reservations);
    }

    @Operation(summary = "Get reservations by status", description = "Retrieve reservations by status. Only accessible by admins.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of reservations retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Reservation.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized access", content = @Content)
    })
    @GetMapping("/status/{status}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Reservation>> getReservationsByStatus(
            @Parameter(description = "Status of the reservations to be retrieved", required = true) @PathVariable @Valid ReservationStatus status) {
        List<Reservation> reservations = reservationService.getReservationsByStatus(status);
        return ResponseEntity.ok(reservations);
    }

    @Operation(summary = "Get reservations by check-in date range", description = "Retrieve reservations by check-in date range. Only accessible by admins.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of reservations retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Reservation.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized access", content = @Content)
    })
    @GetMapping("/checkin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Reservation>> getReservationsByCheckInDateRange(
            @Parameter(description = "Start date of the check-in date range", required = true) @RequestParam Date startDate,
            @Parameter(description = "End date of the check-in date range", required = true) @RequestParam Date endDate) {
        List<Reservation> reservations = reservationService.getReservationsByCheckInDateRange(startDate, endDate);
        return ResponseEntity.ok(reservations);
    }

    @Operation(summary = "Get reservations by check-out date range", description = "Retrieve reservations by check-out date range. Only accessible by admins.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of reservations retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Reservation.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized access", content = @Content)
    })
    @GetMapping("/checkout")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Reservation>> getReservationsByCheckOutDateRange(
            @Parameter(description = "Start date of the check-out date range", required = true) @RequestParam Date startDate,
            @Parameter(description = "End date of the check-out date range", required = true) @RequestParam Date endDate) {
        List<Reservation> reservations = reservationService.getReservationsByCheckOutDateRange(startDate, endDate);
        return ResponseEntity.ok(reservations);
    }
}
