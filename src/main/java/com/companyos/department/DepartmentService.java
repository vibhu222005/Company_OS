package com.companyos.department;

import com.companyos.company.CompanyRepository;
import com.companyos.department.dto.DepartmentRequest;
import com.companyos.department.dto.DepartmentResponse;
import com.companyos.exception.DepartmentCompanyNotFoundException;
import com.companyos.exception.DepartmentNotFoundException;
import com.companyos.exception.DuplicateDepartmentException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final CompanyRepository companyRepository;
    private final DepartmentMapper departmentMapper;

    public DepartmentService(
            DepartmentRepository departmentRepository,
            CompanyRepository companyRepository,
            DepartmentMapper departmentMapper) {

        this.departmentRepository = departmentRepository;
        this.companyRepository = companyRepository;
        this.departmentMapper = departmentMapper;
    }

    public DepartmentResponse createDepartment(DepartmentRequest request) {

        if (!companyRepository.existsById(request.getCompanyId())) {
            throw new DepartmentCompanyNotFoundException(
                    "Company not found with id: " + request.getCompanyId()
            );
        }

        if (departmentRepository.existsByCompanyIdAndName(
                request.getCompanyId(),
                request.getName())) {

            throw new DuplicateDepartmentException(
                    "Department already exists with name: " + request.getName()
            );
        }

        Department department = departmentMapper.toEntity(request);

        Department savedDepartment = departmentRepository.save(department);

        return departmentMapper.toResponse(savedDepartment);
    }

    public List<DepartmentResponse> getAllDepartments() {

        return departmentRepository.findAll()
                .stream()
                .map(departmentMapper::toResponse)
                .toList();
    }

    public DepartmentResponse getDepartmentById(Long id) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() ->
                        new DepartmentNotFoundException(
                                "Department not found with id: " + id
                        )
                );

        return departmentMapper.toResponse(department);
    }

    public List<DepartmentResponse> getDepartmentsByCompany(Long companyId) {

        if (!companyRepository.existsById(companyId)) {
            throw new DepartmentCompanyNotFoundException(
                    "Company not found with id: " + companyId
            );
        }

        return departmentRepository.findByCompanyId(companyId)
                .stream()
                .map(departmentMapper::toResponse)
                .toList();
    }

    public DepartmentResponse updateDepartment(
            Long id,
            DepartmentRequest request) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() ->
                        new DepartmentNotFoundException(
                                "Department not found with id: " + id
                        )
                );

        if (!companyRepository.existsById(request.getCompanyId())) {
            throw new DepartmentCompanyNotFoundException(
                    "Company not found with id: " + request.getCompanyId()
            );
        }

        boolean duplicateExists =
                departmentRepository.existsByCompanyIdAndName(
                        request.getCompanyId(),
                        request.getName()
                );

        if (duplicateExists
                && (!department.getCompanyId().equals(request.getCompanyId())
                || !department.getName().equals(request.getName()))) {

            throw new DuplicateDepartmentException(
                    "Department already exists with name: " + request.getName()
            );
        }

        department.setCompanyId(request.getCompanyId());
        department.setName(request.getName());

        Department updatedDepartment =
                departmentRepository.save(department);

        return departmentMapper.toResponse(updatedDepartment);
    }

    public void deleteDepartment(Long id) {

        if (!departmentRepository.existsById(id)) {
            throw new DepartmentNotFoundException(
                    "Department not found with id: " + id
            );
        }

        departmentRepository.deleteById(id);
    }
}