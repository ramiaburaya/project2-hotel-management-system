package com.example.hotelmangment.housekeeping.Model;

import com.example.hotelmangment.housekeeping.Model.Housekeeping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface HousekeepingRepository extends JpaRepository<Housekeeping, Long> {
}
