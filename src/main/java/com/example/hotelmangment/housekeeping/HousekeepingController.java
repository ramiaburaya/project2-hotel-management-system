package com.example.hotelmangment.housekeeping;

import com.example.hotelmangment.housekeeping.Model.Housekeeping;
import com.example.hotelmangment.housekeeping.Service.HousekeepingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/housekeeping")
public class HousekeepingController {
    @Autowired
    private HousekeepingService housekeepingService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")

    public ResponseEntity<List<Housekeeping>> getAllHousekeepingTasks() {
        List<Housekeeping> tasks = housekeepingService.getAllHousekeepingTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Housekeeping> getHousekeepingTaskById(@PathVariable Long id) {
        Housekeeping task = housekeepingService.getHousekeepingTaskById(id);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Housekeeping> createHousekeepingTask(@RequestBody Housekeeping housekeeping) {
        Housekeeping newTask = housekeepingService.createHousekeepingTask(housekeeping);
        return new ResponseEntity<>(newTask, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Housekeeping> updateHousekeepingTask(@PathVariable Long id, @RequestBody Housekeeping housekeeping) {
        Housekeeping updatedTask = housekeepingService.updateHousekeepingTask(id, housekeeping);
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteHousekeepingTask(@PathVariable Long id) {
        housekeepingService.deleteHousekeepingTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
