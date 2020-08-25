package com.exam.richer.EmployeeCrud.repository;

import com.exam.richer.EmployeeCrud.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    public Optional<EmployeeEntity> findByEmail(String email);

}
