package com.companyos.department.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class DepartmentRequest {

    @NotNull(message = "Company ID is required")
    private Long companyId;

    @NotBlank(message = "Department name is required")
    private String name;

    public DepartmentRequest() {
    }

    public DepartmentRequest(Long companyId, String name) {
        this.companyId = companyId;
        this.name = name;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}