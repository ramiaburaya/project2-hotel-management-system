package com.example.hotelmangment.billing;

import com.example.hotelmangment.billing.Model.Billing;
import com.example.hotelmangment.billing.Service.BillingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/billing")
@Tag(name = "Billing", description = "API for billing management")

public class BillingController {

    @Autowired
    private BillingService billingService;

    @PostMapping
    @Operation(summary = "Create a new billing record", description = "Create a new billing record. Only accessible by admins.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Billing record created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Billing.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized access", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Billing> createBilling(
            @Parameter(description = "Billing object to be created", required = true) @RequestBody @Valid Billing billing) {
        Billing createdBilling = billingService.createBilling(billing);
        return ResponseEntity.ok(createdBilling);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing billing record", description = "Update an existing billing record. Only accessible by admins.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Billing record updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Billing.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized access", content = @Content),
            @ApiResponse(responseCode = "404", description = "Billing record not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Billing> updateBilling(
            @Parameter(description = "ID of the billing record to be updated", required = true) @PathVariable Long id,
            @Parameter(description = "Updated billing object", required = true) @RequestBody @Valid Billing billing) {
        Billing updatedBilling = billingService.updateBilling(id, billing);
        return ResponseEntity.ok(updatedBilling);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a billing record", description = "Delete a billing record. Only accessible by admins.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Billing record deleted successfully", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized access", content = @Content),
            @ApiResponse(responseCode = "404", description = "Billing record not found", content = @Content)
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteBilling(
            @Parameter(description = "ID of the billing record to be deleted", required = true) @PathVariable Long id) {
        billingService.deleteBilling(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get billing record by ID", description = "Retrieve a billing record by its ID. Only accessible by admins.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Billing record retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Billing.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized access", content = @Content),
            @ApiResponse(responseCode = "404", description = "Billing record not found", content = @Content)
    })

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Billing> getBillingById(
            @Parameter(description = "ID of the billing record to be retrieved", required = true) @PathVariable Long id) {
        Billing billing = billingService.getBillingById(id);
        return ResponseEntity.ok(billing);
    }

    @GetMapping
    @Operation(summary = "Get all billing records", description = "Retrieve a list of all billing records. Only accessible by admins.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of billing records retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Billing.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized access", content = @Content)
    })

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Billing>> getAllBillings() {
        List<Billing> billings = billingService.getAllBillings();
        return ResponseEntity.ok(billings);
    }
}
