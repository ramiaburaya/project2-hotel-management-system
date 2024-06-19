package com.example.hotelmangment.customer.Model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Schema(description = "Customer entity representing a customer")
public class Customer {

    @Schema(description = "Unique identifier of the customer", example = "1")
    @Id
    @Column(name = "customer_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "First name of the customer", required = true, example = "John")
    @NotBlank(message = "First name is required")
    @Column(name = "first_name", nullable = false)
    @NonNull
    private String firstName;

    @Schema(description = "Last name of the customer", required = true, example = "Doe")
    @NotBlank(message = "Last name is required")
    @Column(name = "last_name", nullable = false)
    @NonNull
    private String lastName;

    @Schema(description = "Email address of the customer", required = true, example = "john.doe@example.com")
    @NotBlank(message = "Email address is required")
    @Column(name = "email_address", nullable = false, unique = true)
    @Email(message = "Must be in email format, e.g., example@example.com")
    @NonNull
    private String email;

    @Schema(description = "Phone number of the customer", required = true, example = "0591234567")
    @NotBlank(message = "Phone number is required")
    @Column(name = "phone_number", nullable = false, unique = true)
    @Size(min = 10, max = 10, message = "Phone number length must be 10 and start with 059xxxxxxx")
    @NonNull
    private String phoneNumber;

    @Schema(description = "Password of the customer", required = true, example = "Password@123")
    @NotNull
    @Column(name = "password", nullable = false)
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @NonNull
    @Pattern.List({
            @Pattern(regexp = ".*\\d.*", message = "Password must contain at least one digit"),
            @Pattern(regexp = ".*[a-zA-Z].*", message = "Password must contain at least one letter"),
            @Pattern(regexp = ".*[@#$%^&+=!].*", message = "Password must contain at least one special character (@#$%^&+=!)")
    })
    private String password;

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
