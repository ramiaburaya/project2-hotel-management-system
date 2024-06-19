package com.example.hotelmangment.room.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByStatus(RoomStatus status);
    List<Room> findByRoomType(RoomType roomType);
    List<Room> findByCapacityGreaterThanEqual(int capacity);
}
