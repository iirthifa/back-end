package com.bit.backend.repositories;

import com.bit.backend.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    @Query(value = "SELECT MAX(CAST(eeid AS UNSIGNED)) FROM employee", nativeQuery = true)
    Integer findMaxEeidNumber();
}