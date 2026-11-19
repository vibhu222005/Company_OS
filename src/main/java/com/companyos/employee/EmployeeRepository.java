package com.companyos.employee;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository
        extends JpaRepository<Employee, Long> {
    List<Employee> findByCompanyId(Long companyId);

    boolean existsByEmail(String email);
}