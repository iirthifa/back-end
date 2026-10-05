package com.bit.backend.repositories;

import com.bit.backend.entities.HolidayEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HolidayRepository extends JpaRepository<HolidayEntity, Long> {
}
