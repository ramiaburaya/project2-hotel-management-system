package com.example.hotelmangment.customer.Model;

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
public class Customer {
    @Id
    @Column(name = "customer_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "first name is required")
    @Column(name = "first_name", nullable = false)
    @NonNull
    private String firstName;

    @NotBlank(message = "last name is required")
    @Column(name = "last_name", nullable = false)
    @NonNull
    private String lastName;

    @NotBlank(message = "email address is required")
    @Column(name = "email_address", nullable = false, unique = true)
    @Email(message = "Must be in email format, e.g., example@example.com")
    @NonNull
    private String email;

    @NotBlank(message = "phone number is required")
    @Column(name = "phone_number", nullable = false, unique = true)
    @Size(min = 10, max = 10, message = "Phone number length must be 10 start with 059xxxxxxx")
    @NonNull
    private String phoneNumber;

    @NotNull
    @Column(name = "password", nullable = false)
    @Size(min = 8, message = "Password must at least 8 characters")
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


