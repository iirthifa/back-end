package com.bit.backend.repositories;

import com.bit.backend.entities.RosterEntryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RosterEntryRepository extends JpaRepository<RosterEntryEntity, Long> {
}
