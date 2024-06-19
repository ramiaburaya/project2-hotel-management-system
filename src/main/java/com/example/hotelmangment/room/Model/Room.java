package com.example.hotelmangment.room.Model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Data
@ToString
@Schema(description = "Room entity representing a room in the hotel")
public class Room {

    @Schema(description = "Unique identifier of the room", example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id", nullable = false)
    private Long id;

    @Schema(description = "Room number", required = true, example = "101")
    @NotNull
    @Column(name = "room_number", nullable = false, unique = true)
    private Integer roomNumber;

    @Schema(description = "Status of the room", required = true, example = "AVAILABLE")
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private RoomStatus status;

    @Schema(description = "Type of the room", required = true, example = "DELUXE")
    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "room_type", nullable = false)
    private RoomType roomType;

    @Schema(description = "Price of the room", required = true, example = "100.00")
    @Column(name = "price", nullable = false)
    @NotNull
    @DecimalMin(value = "0.0", message = "Amount must be greater than or equal to 0", inclusive = false)
    private BigDecimal price;

    @Schema(description = "Capacity of the room", required = true, example = "2")
    @Positive(message = "Capacity must be greater than 0")
    @Column(name = "capacity", nullable = false)
    private int capacity;

    @Schema(description = "Facilities available in the room", example = "Wi-Fi, TV, Air Conditioning")
    @Column(name = "facilities")
    private String facilities;

    @Schema(description = "Features of the room", example = "Ocean view, Balcony")
    @Column(name = "features")
    private String features;

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
