package com.bit.backend.repositories;

import com.bit.backend.entities.MohEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MohRepository extends JpaRepository<MohEntity, Long> {
}
