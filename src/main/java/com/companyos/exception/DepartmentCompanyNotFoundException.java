package com.companyos.exception;

public class DepartmentCompanyNotFoundException extends RuntimeException {

    public DepartmentCompanyNotFoundException(String message) {
        super(message);
    }
}