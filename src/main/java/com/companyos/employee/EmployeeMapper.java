package com.companyos.employee;

import com.companyos.employee.dto.EmployeeResponse;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public EmployeeResponse toResponse(Employee employee) {
        return new EmployeeResponse(
                employee.getId(),
                employee.getCompanyId(),
                employee.getName(),
                employee.getEmail(),
                employee.getDesignation(),
                employee.getCreatedAt()
        );
    }
}