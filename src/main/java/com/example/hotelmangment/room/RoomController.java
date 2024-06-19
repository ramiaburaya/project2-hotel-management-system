package com.example.hotelmangment.room;

import com.example.hotelmangment.room.Model.Room;
import com.example.hotelmangment.room.Model.RoomStatus;
import com.example.hotelmangment.room.Model.RoomType;
import com.example.hotelmangment.room.Service.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/room")
@Tag(name = "Room", description = "API for room management")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Operation(summary = "Create a new room", description = "Create a new room with the provided details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Room created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Room.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Room> createRoom(
            @Parameter(description = "Room object to be created", required = true) @RequestBody  Room room) {
        Room createdRoom = roomService.createRoom(room);
        return ResponseEntity.ok(createdRoom);
    }

    @Operation(summary = "Update an existing room", description = "Update an existing room with the provided details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Room updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Room.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
            @ApiResponse(responseCode = "404", description = "Room not found", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Room> updateRoom(
            @Parameter(description = "ID of the room to be updated", required = true) @PathVariable Long id,
            @Parameter(description = "Updated room object", required = true) @RequestBody  Room room) {
        Room updatedRoom = roomService.updateRoom(id, room);
        return ResponseEntity.ok(updatedRoom);
    }

    @Operation(summary = "Delete a room", description = "Delete a room by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Room deleted successfully", content = @Content),
            @ApiResponse(responseCode = "404", description = "Room not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(
            @Parameter(description = "ID of the room to be deleted", required = true) @PathVariable Long id) {
        roomService.deleteRoom(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get room by ID", description = "Retrieve a room by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Room retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Room.class))),
            @ApiResponse(responseCode = "404", description = "Room not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(
            @Parameter(description = "ID of the room to be retrieved", required = true) @PathVariable Long id) {
        Room room = roomService.getRoomById(id);
        return ResponseEntity.ok(room);
    }

    @Operation(summary = "Get all rooms", description = "Retrieve a list of all rooms.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of rooms retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Room.class)))
    })
    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms() {
        List<Room> rooms = roomService.getAllRooms();
        return ResponseEntity.ok(rooms);
    }

    @Operation(summary = "Get rooms by status", description = "Retrieve rooms by their status.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of rooms retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Room.class))),
            @ApiResponse(responseCode = "404", description = "No rooms found with the specified status", content = @Content)
    })
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Room>> getRoomsByStatus(
            @Parameter(description = "Status of the rooms to be retrieved", required = true) @PathVariable RoomStatus status) {
        List<Room> rooms = roomService.getRoomsByStatus(status);
        return ResponseEntity.ok(rooms);
    }

    @Operation(summary = "Get rooms by type", description = "Retrieve rooms by their type.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of rooms retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Room.class))),
            @ApiResponse(responseCode = "404", description = "No rooms found with the specified type", content = @Content)
    })
    @GetMapping("/type/{roomType}")
    public ResponseEntity<List<Room>> getRoomsByType(
            @Parameter(description = "Type of the rooms to be retrieved", required = true) @PathVariable RoomType roomType) {
        List<Room> rooms = roomService.getRoomsByType(roomType);
        return ResponseEntity.ok(rooms);
    }

    @Operation(summary = "Get rooms by capacity", description = "Retrieve rooms by their capacity.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of rooms retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Room.class))),
            @ApiResponse(responseCode = "404", description = "No rooms found with the specified capacity", content = @Content)
    })
    @GetMapping("/capacity")
    public ResponseEntity<List<Room>> getRoomsByCapacity(
            @Parameter(description = "Capacity of the rooms to be retrieved", required = true) @RequestParam int capacity) {
        List<Room> rooms = roomService.getRoomsByCapacity(capacity);
        return ResponseEntity.ok(rooms);
    }
}
