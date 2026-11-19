package com.companyos.exception;

public class EmployeeCompanyNotFoundException extends RuntimeException {

    public EmployeeCompanyNotFoundException(String message) {
        super(message);
    }
}