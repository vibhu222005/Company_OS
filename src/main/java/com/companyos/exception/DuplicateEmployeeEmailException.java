package com.companyos.exception;

public class DuplicateEmployeeEmailException extends RuntimeException {

    public DuplicateEmployeeEmailException(String message) {
        super(message);
    }
}