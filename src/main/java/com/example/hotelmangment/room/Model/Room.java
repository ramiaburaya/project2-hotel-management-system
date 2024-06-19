package com.example.hotelmangment.room.Model;

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
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "room_number", nullable = false, unique = true)
    private Integer roomNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private RoomStatus status;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "room_type", nullable = false)
    private RoomType roomType;

    @Column(name = "price", nullable = false)
    @NotNull
    @DecimalMin(value = "0.0", message = "Amount must be greater than or equal to 0", inclusive = false)
    private BigDecimal price;

    @Positive(message = "Capacity must be greater than 0")
    @Column(name = "capacity", nullable = false)
    private int capacity;

    @Column(name = "facilities")
    private String facilities;


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
