package com.example.hotelmangment.employee.Model;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Employee entity representing an employee record")
public class Employee {

    @Schema(description = "Unique identifier of the employee", example = "1")
    @Id
    @Column(name = "employee_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "First name of the employee", required = true, example = "John")
    @NotBlank(message = "first name is required")
    @NonNull
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Schema(description = "Last name of the employee", required = true, example = "Doe")
    @NotBlank(message = "last Name is required")
    @NonNull
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Schema(description = "Email address of the employee", required = true, example = "john.doe@example.com")
    @NotBlank(message = "email address is required")
    @NonNull
    @Column(name = "email_address", nullable = false, unique = true)
    @Email(message = "Must be in email format, e.g., example@example.com")
    private String email;

    @Schema(description = "Phone number of the employee", required = true, example = "0591234567")
    @NotBlank(message = "phone number is required")
    @NonNull
    @Column(name = "phone_number", nullable = false, unique = true)
    @Size(min = 10, max = 10, message = "Phone number length must be 10 start with 059xxxxxxx")
    private String phoneNumber;

    @Schema(description = "Address of the employee", required = true, example = "123 Main St")
    @NotBlank(message = "address is required")
    @NonNull
    @Column(name = "address", nullable = false)
    private String address;

    @Schema(description = "Role of the employee", required = true, example = "Manager")
    @NotBlank(message = "RoleName is required")
    @NonNull
    @Column(name = "role", nullable = false, length = 50)
    private String role;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @NotNull
    @Column(name = "updated_at", nullable = false)
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
