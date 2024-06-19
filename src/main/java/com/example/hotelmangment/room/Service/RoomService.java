package com.example.hotelmangment.room.Service;

import com.example.hotelmangment.room.Model.Room;
import com.example.hotelmangment.room.Model.RoomStatus;
import com.example.hotelmangment.room.Model.RoomType;

import java.util.List;

public interface RoomService {
    Room createRoom(Room room);
    Room updateRoom(Long id, Room room);
    void deleteRoom(Long id);
    Room getRoomById(Long id);
    List<Room> getAllRooms();
    List<Room> getRoomsByStatus(RoomStatus status);
    List<Room> getRoomsByType(RoomType roomType);
    List<Room> getRoomsByCapacity(int capacity);
}
