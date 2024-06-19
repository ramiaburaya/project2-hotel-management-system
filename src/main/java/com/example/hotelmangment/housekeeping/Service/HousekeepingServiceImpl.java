package com.example.hotelmangment.housekeeping.Service;

import com.example.hotelmangment.housekeeping.HousekeepingException.HousekeepingNotFoundException;
import com.example.hotelmangment.housekeeping.Model.Housekeeping;
import com.example.hotelmangment.housekeeping.Model.HousekeepingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class HousekeepingServiceImpl implements HousekeepingService {

    @Autowired
    HousekeepingRepository housekeepingRepository;

    @Override
    public List<Housekeeping> getAllHousekeepingTasks() {
        return housekeepingRepository.findAll();
    }

    @Override
    public Housekeeping getHousekeepingTaskById(Long id) {
        return housekeepingRepository.findById(id).orElseThrow(HousekeepingNotFoundException::new);
    }

    @Override
    public Housekeeping createHousekeepingTask(Housekeeping housekeeping) {
        return housekeepingRepository.save(housekeeping);
    }

    @Override
    public void deleteHousekeepingTask(Long id) {
        Housekeeping housekeeping = getHousekeepingTaskById(id);
        housekeepingRepository.delete(housekeeping);
    }

    @Override
    public Housekeeping updateHousekeepingTask(Long id, Housekeeping housekeeping) {
        Housekeeping housekeepingTaskById = getHousekeepingTaskById(id);

        housekeepingTaskById.setEmployee(housekeeping.getEmployee());
        housekeepingTaskById.setRoom(housekeeping.getRoom());
        housekeepingTaskById.setTaskDescription(housekeeping.getTaskDescription());
        housekeepingTaskById.setScheduleDate(housekeeping.getScheduleDate());
        housekeepingTaskById.setStatus(housekeeping.getStatus());
        return housekeepingRepository.save(housekeepingTaskById);
    }
}
