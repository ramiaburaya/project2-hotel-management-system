package com.example.hotelmangment.reservation.Model;

import com.example.hotelmangment.customer.Model.Customer;
import com.example.hotelmangment.room.Model.Room;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Data
@Schema(description = "Reservation entity representing a reservation record")
public class Reservation {

    @Schema(description = "Unique identifier of the reservation", example = "1")
    @Id
    @Column(name = "reservation_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Customer who made the reservation", required = true)
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Schema(description = "Room reserved", required = true)
    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @Schema(description = "Check-in date", required = true, example = "2024-06-19")
    @NotNull
    @Column(name = "check_in_date", nullable = false)
    @DateTimeFormat // ISO 8601 Format yyyy-mm-dd
    private Date checkInDate;

    @Schema(description = "Check-out date", required = true, example = "2024-06-19")
    @NotNull
    @Column(name = "check_out_date", nullable = false)
    @DateTimeFormat
    private Date checkOutDate;

    @Schema(description = "Status of the reservation", required = true, example = "CONFIRMED")
    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "status", nullable = false)
    private ReservationStatus status;

    @Column(name = "created_at", nullable = false)
    @NotNull
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    @NotNull
    private Timestamp updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Timestamp(System.currentTimeMillis());
        updatedAt = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Timestamp(System.currentTimeMillis());
    }
}
