package com.example.hotelmangment.customer;


import com.example.hotelmangment.customer.Model.Customer;
import com.example.hotelmangment.customer.Service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customer")
@Tag(name = "Customer", description = "API for customer management")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Get all customers", description = "Retrieve a list of all customers. Only accessible by admins.", responses = {
            @ApiResponse(description = "List of customers retrieved successfully", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomerDto.class))),
            @ApiResponse(description = "Unauthorized access", responseCode = "401", content = @Content),
    })
    ResponseEntity<List<CustomerDto>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();

        List<CustomerDto> customerDTOs = customers.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(customerDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get customer details", description = "Retrieve details of a specific customer by ID. Accessible by admins and the customer themselves.", responses = {
            @ApiResponse(description = "Customer details retrieved successfully", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomerDto.class))),
            @ApiResponse(description = "Customer not found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Unauthorized access", responseCode = "401", content = @Content)
    })
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CUSTOMER')")
    ResponseEntity<Customer> viewDetails(  @Parameter(description = "ID of the customer to be retrieved", required = true) @PathVariable Long id) {
        return new ResponseEntity<>(customerService.ViewUserDetails(id), HttpStatus.OK);
    }

    @PostMapping("/register")
    @Operation(summary = "Register a new customer", description = "Register a new customer with the required details.", responses = {
            @ApiResponse(description = "Customer registered successfully", responseCode = "201", content = @Content),
            @ApiResponse(description = "Validation error", responseCode = "400", content = @Content)
    })
    ResponseEntity<String> register(@Parameter(description = "Customer object to be created", required = true) @RequestBody @Valid Customer customer) {
        customerService.register(customer);
        return new ResponseEntity<>("Customer registered successfully", HttpStatus.CREATED);
    }

    @PatchMapping("/changePassword")
    @Operation(summary = "Change password", description = "Change the password of the logged-in customer.", responses = {
            @ApiResponse(description = "Password changed successfully", responseCode = "200", content = @Content),
            @ApiResponse(description = "Unauthorized access", responseCode = "401", content = @Content),
            @ApiResponse(description = "Validation error", responseCode = "400", content = @Content)
    })
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    ResponseEntity<String> changePassword(@Parameter(description = "ID of the customer", required = true)@RequestBody Long id,
                                          @Parameter(description = "Current password", required = true) @RequestParam String currentPassword,
                                          @Parameter(description = "New password", required = true) @RequestParam String newPassword) {
        customerService.changePassword(id, currentPassword, newPassword);
        return new ResponseEntity<>("Password changed successfully", HttpStatus.OK);
    }

    @PatchMapping("/updateEmail")
    @Operation(summary = "Update email", description = "Update the email of the logged-in customer.", responses = {
            @ApiResponse(description = "Email updated successfully", responseCode = "200", content = @Content),
            @ApiResponse(description = "Unauthorized access", responseCode = "401", content = @Content),
            @ApiResponse(description = "Validation error", responseCode = "400", content = @Content)
    })
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    ResponseEntity<String> changeEmail(@Parameter(description = "ID of the customer", required = true) @RequestParam Long id,
                                       @Parameter(description = "New email address", required = true) @RequestParam String newEmail) {
        customerService.changeEmail(id, newEmail);
        return new ResponseEntity<>("Email changed successfully", HttpStatus.OK);
    }

    @PatchMapping("/updatePhone")
    @Operation(summary = "Update phone number", description = "Update the phone number of the logged-in customer.", responses = {
            @ApiResponse(description = "Phone number updated successfully", responseCode = "200", content = @Content),
            @ApiResponse(description = "Unauthorized access", responseCode = "401", content = @Content),
            @ApiResponse(description = "Validation error", responseCode = "400", content = @Content)
    })
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    ResponseEntity<String> changePhone(  @Parameter(description = "ID of the customer", required = true) @RequestParam Long id,
                                         @Parameter(description = "New phone number", required = true) @RequestParam String newPhone) {
        customerService.changePhone(id, newPhone);
        return new ResponseEntity<>("Phone number changed successfully", HttpStatus.OK);
    }

    public CustomerDto convertToDto(Customer customer) {
        return new CustomerDto(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getPhoneNumber());
    }
}
