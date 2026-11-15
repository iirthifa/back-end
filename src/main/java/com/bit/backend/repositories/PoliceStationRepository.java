package com.bit.backend.repositories;

import com.bit.backend.entities.PoliceStationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PoliceStationRepository extends JpaRepository<PoliceStationEntity, Long> {
}
