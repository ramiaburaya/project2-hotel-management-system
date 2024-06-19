package com.example.hotelmangment.employee.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @Column(name = "employee_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "first name is required")
    @NonNull
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank(message = "last Name is required")
    @NonNull
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotBlank(message = "email address is required")
    @NonNull
    @Column(name = "email_address", nullable = false, unique = true)
    @Email(message = "Must be in email format, e.g., example@example.com")
    private String email;

    @NotBlank(message = "phone number is required")
    @NonNull
    @Column(name = "phone_number", nullable = false, unique = true)
    @Size(min = 10, max = 10, message = "Phone number length must be 10 start with 059xxxxxxx")
    private String phoneNumber;

    @NotBlank(message = "address is required")
    @NonNull
    @Column(name = "address", nullable = false)
    private String address;

    @NotBlank(message = "RoleName is required")
    @NonNull
    @Column(name = "role", nullable = false, length = 50)
    private String role;

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
