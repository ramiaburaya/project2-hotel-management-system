package com.example.hotelmangment.housekeeping.Model;

import com.example.hotelmangment.employee.Model.Employee;
import com.example.hotelmangment.room.Model.Room;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Data
public class Housekeeping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "housekeeping_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "status", nullable = false)
    private HousekeepingStatus status;

    @Column(name = "task_description", nullable = false)
    private String taskDescription;

    @Column(name = "schedule_date", nullable = false)
    @NotNull
    @DateTimeFormat
    private Date scheduleDate;


    @Column(name = "created_at", nullable = false)
    @NotNull
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    @NotNull
    private Timestamp updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Timestamp(System.currentTimeMillis());
        updatedAt = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Timestamp(System.currentTimeMillis());
    }
}
