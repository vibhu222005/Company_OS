package com.companyos.department;

import com.companyos.department.dto.DepartmentRequest;
import com.companyos.department.dto.DepartmentResponse;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {

    public Department toEntity(DepartmentRequest request) {
        return new Department(
                request.getCompanyId(),
                request.getName()
        );
    }

    public DepartmentResponse toResponse(Department department) {
        return new DepartmentResponse(
                department.getId(),
                department.getCompanyId(),
                department.getName(),
                department.getCreatedAt()
        );
    }
}