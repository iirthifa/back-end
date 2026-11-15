package com.bit.backend.repositories;

import com.bit.backend.entities.NationalityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NationalityRepository extends JpaRepository<NationalityEntity, Long> {
}
