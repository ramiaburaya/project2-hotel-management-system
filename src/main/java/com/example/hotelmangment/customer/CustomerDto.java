package com.example.hotelmangment.customer;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Schema(description = "Customer data transfer object")

public class CustomerDto {

    @NonNull
    @NotBlank(message = "ID is required")
    @Schema(description = "Unique identifier of the customer", example = "1")
    private Long id;

    @NonNull
    @NotBlank(message = "First name is required")
    @Schema(description = "First name of the customer", example = "John")
    private String firstName;

    @NonNull
    @NotBlank(message = "Last name is required")
    @Schema(description = "Last name of the customer", example = "Doe")
    private String lastName;

    @NonNull
    @NotBlank(message = "Email is required")
    @Email(message = "Must be in email format")
    @Schema(description = "Email address of the customer", example = "john.doe@example.com")
    private String email;

    @NonNull
    @NotBlank(message = "Phone number is required")
    @Size(min = 10, max = 10, message = "Phone number length must be 10 digits")
    @Schema(description = "Phone number of the customer", example = "0591234567")
    private String phoneNumber;

}
