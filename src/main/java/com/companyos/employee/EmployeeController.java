package com.companyos.employee;

import com.companyos.employee.dto.EmployeeRequest;
import com.companyos.employee.dto.EmployeeResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    public EmployeeController(
            EmployeeService employeeService,
            EmployeeMapper employeeMapper
    ) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @PostMapping
    public EmployeeResponse createEmployee(
            @Valid @RequestBody EmployeeRequest request
    ) {
        Employee employee = new Employee(
                request.getCompanyId(),
                request.getName(),
                request.getEmail(),
                request.getDesignation()
        );

        Employee savedEmployee =
                employeeService.createEmployee(employee);

        return employeeMapper.toResponse(savedEmployee);
    }

    @GetMapping
    public List<EmployeeResponse> getAllEmployees() {
        return employeeService.getAllEmployees()
                .stream()
                .map(employeeMapper::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(
            @PathVariable Long id
    ) {
        return employeeService.getEmployeeById(id)
                .map(employee -> ResponseEntity.ok(
                        employeeMapper.toResponse(employee)
                ))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeRequest request
    ) {
        Employee updatedEmployee = new Employee(
                request.getCompanyId(),
                request.getName(),
                request.getEmail(),
                request.getDesignation()
        );

        return employeeService.updateEmployee(id, updatedEmployee)
                .map(employee -> ResponseEntity.ok(
                        employeeMapper.toResponse(employee)
                ))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(
            @PathVariable Long id
    ) {
        boolean deleted = employeeService.deleteEmployee(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
    @GetMapping("/company/{companyId}")
    public List<EmployeeResponse> getEmployeesByCompanyId(@PathVariable Long companyId) {
        return employeeService.getEmployeesByCompanyId(companyId)
                .stream()
                .map(employeeMapper::toResponse)
                .toList();
    }
}