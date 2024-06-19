package com.example.hotelmangment.billing.Model;

import com.example.hotelmangment.reservation.Model.Reservation;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Schema(description = "Billing entity representing a billing record")

public class Billing {

    @Column(name = "billing_id", nullable = false) // ensure the DB level not null
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull // ensure the Java Bean fot not null
    @Id
    @Schema(description = "Unique identifier of the billing record", example = "1")
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "reservation_id", nullable = false)
    @Schema(description = "Associated reservation for the billing record", required = true)
    private Reservation reservation;

    @Column(name = "amount", nullable = false)
    @NotNull
    @DecimalMin(value = "0.0", message = "Amount must be greater than or equal to 0", inclusive = false)
    // inclusive means it can be 0 or bigger
    @Schema(description = "Amount billed", required = true, example = "100.50")
    private BigDecimal amount;

    @Column(name = "billing_date", nullable = false)
    @NotNull
    @Schema(description = "Billing date", required = true, example = "2024-06-19")
    private Date billingDate;

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
