package com.example.hotelmangment.housekeeping.Service;

import com.example.hotelmangment.housekeeping.Model.Housekeeping;
import org.springframework.stereotype.Service;

import java.util.List;

public interface HousekeepingService {

    List<Housekeeping> getAllHousekeepingTasks();

    Housekeeping getHousekeepingTaskById(Long id);

    Housekeeping createHousekeepingTask(Housekeeping housekeeping);

    void deleteHousekeepingTask(Long id);

    Housekeeping updateHousekeepingTask(Long id, Housekeeping housekeeping);

}
