package com.example.hotelmangment.housekeeping;

import com.example.hotelmangment.housekeeping.Model.Housekeeping;
import com.example.hotelmangment.housekeeping.Service.HousekeepingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/housekeeping")
@Tag(name = "Housekeeping", description = "API for housekeeping management")
public class HousekeepingController {

    @Autowired
    private HousekeepingService housekeepingService;

    @Operation(summary = "Get all housekeeping tasks", description = "Retrieve a list of all housekeeping tasks. Only accessible by admins.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of housekeeping tasks retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Housekeeping.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized access", content = @Content)
    })
    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Housekeeping>> getAllHousekeepingTasks() {
        List<Housekeeping> tasks = housekeepingService.getAllHousekeepingTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @Operation(summary = "Get housekeeping task by ID", description = "Retrieve a housekeeping task by its ID. Only accessible by admins.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Housekeeping task retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Housekeeping.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized access", content = @Content),
            @ApiResponse(responseCode = "404", description = "Housekeeping task not found", content = @Content)
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Housekeeping> getHousekeepingTaskById(
            @Parameter(description = "ID of the housekeeping task to be retrieved", required = true) @PathVariable Long id) {
        Housekeeping task = housekeepingService.getHousekeepingTaskById(id);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @Operation(summary = "Create a new housekeeping task", description = "Create a new housekeeping task. Only accessible by admins.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Housekeeping task created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Housekeeping.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized access", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Housekeeping> createHousekeepingTask(
            @Parameter(description = "Housekeeping object to be created", required = true) @RequestBody  Housekeeping housekeeping) {
        Housekeeping newTask = housekeepingService.createHousekeepingTask(housekeeping);
        return new ResponseEntity<>(newTask, HttpStatus.CREATED);
    }

    @Operation(summary = "Update a housekeeping task", description = "Update a housekeeping task. Only accessible by admins.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Housekeeping task updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Housekeeping.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized access", content = @Content),
            @ApiResponse(responseCode = "404", description = "Housekeeping task not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Housekeeping> updateHousekeepingTask(
            @Parameter(description = "ID of the housekeeping task to be updated", required = true) @PathVariable Long id,
            @Parameter(description = "Updated housekeeping object", required = true) @RequestBody Housekeeping housekeeping) {
        Housekeeping updatedTask = housekeepingService.updateHousekeepingTask(id, housekeeping);
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

    @Operation(summary = "Delete a housekeeping task", description = "Delete a housekeeping task. Only accessible by admins.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Housekeeping task deleted successfully", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized access", content = @Content),
            @ApiResponse(responseCode = "404", description = "Housekeeping task not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteHousekeepingTask(
            @Parameter(description = "ID of the housekeeping task to be deleted", required = true) @PathVariable Long id) {
        housekeepingService.deleteHousekeepingTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
