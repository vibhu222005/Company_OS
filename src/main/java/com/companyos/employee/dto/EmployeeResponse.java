package com.companyos.employee.dto;

import java.time.LocalDateTime;

public class EmployeeResponse {

    private Long id;
    private Long companyId;
    private String name;
    private String email;
    private String designation;
    private LocalDateTime createdAt;

    public EmployeeResponse() {
    }

    public EmployeeResponse(
            Long id,
            Long companyId,
            String name,
            String email,
            String designation,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.companyId = companyId;
        this.name = name;
        this.email = email;
        this.designation = designation;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}