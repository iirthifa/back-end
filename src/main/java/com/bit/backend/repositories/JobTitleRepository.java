package com.bit.backend.repositories;

import com.bit.backend.entities.JobTitleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobTitleRepository extends JpaRepository<JobTitleEntity, Long> {
}
