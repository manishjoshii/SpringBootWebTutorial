package com.manishjoshi.SpringBootWebTutorial.repositories;

import com.manishjoshi.SpringBootWebTutorial.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, UUID> {
    boolean existsByEmail(String email);
    @Transactional
    long deleteByEmail(String email);
}
