package com.companyos.department.dto;

import java.time.LocalDateTime;

public class DepartmentResponse {

    private Long id;
    private Long companyId;
    private String name;
    private LocalDateTime createdAt;

    public DepartmentResponse() {
    }

    public DepartmentResponse(Long id, Long companyId, String name, LocalDateTime createdAt) {
        this.id = id;
        this.companyId = companyId;
        this.name = name;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}