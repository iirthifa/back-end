package com.bit.backend.repositories;

import com.bit.backend.entities.WorkingCalendarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkingCalendarRepository extends JpaRepository<WorkingCalendarEntity, Long> {
}
