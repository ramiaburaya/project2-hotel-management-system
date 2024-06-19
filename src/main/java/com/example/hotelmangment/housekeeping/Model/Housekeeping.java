package com.example.hotelmangment.housekeeping.Model;

import com.example.hotelmangment.employee.Model.Employee;
import com.example.hotelmangment.room.Model.Room;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Data
@Schema(description = "Housekeeping entity representing a housekeeping task")
public class Housekeeping {

    @Schema(description = "Unique identifier of the housekeeping task", example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "housekeeping_id", nullable = false)
    private Long id;

    @Schema(description = "Employee assigned to the housekeeping task", required = true)
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Schema(description = "Room associated with the housekeeping task", required = true)
    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @Schema(description = "Status of the housekeeping task", required = true, example = "PENDING")
    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "status", nullable = false)
    private HousekeepingStatus status;

    @Schema(description = "Description of the housekeeping task", required = true, example = "Clean the room and replace linens")
    @Column(name = "task_description", nullable = false)
    private String taskDescription;

    @Schema(description = "Scheduled date for the housekeeping task", required = true, example = "2024-06-19")
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
