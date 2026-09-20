package com.bit.backend.repositories;

import com.bit.backend.entities.DesignationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DesignationRepository extends JpaRepository<DesignationEntity, Long> {
}
