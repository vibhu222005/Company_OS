package com.companyos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.dao.DataIntegrityViolationException;
import com.companyos.exception.DepartmentCompanyNotFoundException;
import com.companyos.exception.DepartmentNotFoundException;
import com.companyos.exception.DuplicateDepartmentException;

import java.util.HashMap;
import java.util.Map;
import com.companyos.exception.DuplicateEmployeeEmailException;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(
            MethodArgumentNotValidException exception
    ) {
        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(error.getField(), error.getDefaultMessage())
                );

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(DuplicateEmployeeEmailException.class)
    public ResponseEntity<Map<String, String>> handleDuplicateEmployeeEmail(
            DuplicateEmployeeEmailException exception
    ) {
        Map<String, String> error = new HashMap<>();
        error.put("error", exception.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(EmployeeCompanyNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleEmployeeCompanyNotFound(
            EmployeeCompanyNotFoundException exception
    ) {
        Map<String, String> error = new HashMap<>();
        error.put("error", exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(
            IllegalArgumentException exception
    ) {
        Map<String, Object> error = new HashMap<>();
        error.put("status", 400);
        error.put("error", exception.getMessage());

        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> handleDataIntegrityViolation(
            DataIntegrityViolationException exception
    ) {
        Map<String, Object> error = new HashMap<>();
        error.put("status", 409);
        error.put("error", "Duplicate or invalid database value");

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleDepartmentNotFound(
            DepartmentNotFoundException exception
    ) {
        Map<String, String> error = new HashMap<>();
        error.put("error", exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DepartmentCompanyNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleDepartmentCompanyNotFound(
            DepartmentCompanyNotFoundException exception
    ) {
        Map<String, String> error = new HashMap<>();
        error.put("error", exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(DuplicateDepartmentException.class)
    public ResponseEntity<Map<String, String>> handleDuplicateDepartment(
            DuplicateDepartmentException exception
    ) {
        Map<String, String> error = new HashMap<>();
        error.put("error", exception.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
}