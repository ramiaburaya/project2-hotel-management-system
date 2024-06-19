package com.example.hotelmangment.room;

import com.example.hotelmangment.room.Model.Room;
import com.example.hotelmangment.room.Model.RoomStatus;
import com.example.hotelmangment.room.Model.RoomType;
import com.example.hotelmangment.room.Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping
    public ResponseEntity<Room> createRoom(@RequestBody Room room) {
        Room createdRoom = roomService.createRoom(room);
        return ResponseEntity.ok(createdRoom);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable Long id, @RequestBody Room room) {
        Room updatedRoom = roomService.updateRoom(id, room);
        return ResponseEntity.ok(updatedRoom);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable Long id) {
        Room room = roomService.getRoomById(id);
        return ResponseEntity.ok(room);
    }

    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms() {
        List<Room> rooms = roomService.getAllRooms();
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Room>> getRoomsByStatus(@PathVariable RoomStatus status) {
        List<Room> rooms = roomService.getRoomsByStatus(status);
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/type/{roomType}")
    public ResponseEntity<List<Room>> getRoomsByType(@PathVariable RoomType roomType) {
        List<Room> rooms = roomService.getRoomsByType(roomType);
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/capacity")
    public ResponseEntity<List<Room>> getRoomsByCapacity(@RequestParam int capacity) {
        List<Room> rooms = roomService.getRoomsByCapacity(capacity);
        return ResponseEntity.ok(rooms);
    }
}