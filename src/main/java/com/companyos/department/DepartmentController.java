package com.companyos.department;

import com.companyos.department.dto.DepartmentRequest;
import com.companyos.department.dto.DepartmentResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DepartmentResponse createDepartment(
            @Valid @RequestBody DepartmentRequest request) {

        return departmentService.createDepartment(request);
    }

    @GetMapping
    public List<DepartmentResponse> getAllDepartments() {

        return departmentService.getAllDepartments();
    }

    @GetMapping("/{id}")
    public DepartmentResponse getDepartmentById(
            @PathVariable Long id) {

        return departmentService.getDepartmentById(id);
    }

    @GetMapping("/company/{companyId}")
    public List<DepartmentResponse> getDepartmentsByCompany(
            @PathVariable Long companyId) {

        return departmentService.getDepartmentsByCompany(companyId);
    }

    @PutMapping("/{id}")
    public DepartmentResponse updateDepartment(
            @PathVariable Long id,
            @Valid @RequestBody DepartmentRequest request) {

        return departmentService.updateDepartment(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDepartment(@PathVariable Long id) {

        departmentService.deleteDepartment(id);
    }
}