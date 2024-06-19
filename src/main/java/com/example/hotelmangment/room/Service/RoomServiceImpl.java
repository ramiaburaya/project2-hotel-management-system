package com.example.hotelmangment.room.Service;

import com.example.hotelmangment.reservation.ReservationException.ResourceNotFoundException;
import com.example.hotelmangment.room.Model.Room;
import com.example.hotelmangment.room.Model.RoomRepository;
import com.example.hotelmangment.room.Model.RoomStatus;
import com.example.hotelmangment.room.Model.RoomType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Room updateRoom(Long id, Room room) {
        Optional<Room> existingRoom = roomRepository.findById(id);
        if (existingRoom.isPresent()) {
            Room updatedRoom = existingRoom.get();
            updatedRoom.setRoomNumber(room.getRoomNumber());
            updatedRoom.setStatus(room.getStatus());
            updatedRoom.setRoomType(room.getRoomType());
            updatedRoom.setPrice(room.getPrice());
            updatedRoom.setCapacity(room.getCapacity());
            updatedRoom.setFacilities(room.getFacilities());
            updatedRoom.setFeatures(room.getFeatures());
            return roomRepository.save(updatedRoom);
        } else {
            throw new ResourceNotFoundException("Room not found with id " + id);
        }
    }

    @Override
    public void deleteRoom(Long id) {
        if (roomRepository.existsById(id)) {
            roomRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Room not found with id " + id);
        }
    }

    @Override
    public Room getRoomById(Long id) {
        return roomRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Room not found with id " + id));
    }

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public List<Room> getRoomsByStatus(RoomStatus status) {
        return roomRepository.findByStatus(status);
    }

    @Override
    public List<Room> getRoomsByType(RoomType roomType) {
        return roomRepository.findByRoomType(roomType);
    }

    @Override
    public List<Room> getRoomsByCapacity(int capacity) {
        return roomRepository.findByCapacityGreaterThanEqual(capacity);
    }
}
