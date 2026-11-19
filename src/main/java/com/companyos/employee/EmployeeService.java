package com.companyos.employee;

import org.springframework.stereotype.Service;
import com.companyos.company.CompanyRepository;
import java.util.List;
import java.util.Optional;
import com.companyos.exception.EmployeeCompanyNotFoundException;
import com.companyos.exception.DuplicateEmployeeEmailException;


@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final CompanyRepository companyRepository;

    public EmployeeService(EmployeeRepository employeeRepository,
                           CompanyRepository companyRepository) {
        this.employeeRepository = employeeRepository;
        this.companyRepository = companyRepository;
    }

    public Employee createEmployee(Employee employee) {
        if (employeeRepository.existsByEmail(employee.getEmail())) {
            throw new DuplicateEmployeeEmailException(
                    "Employee email already exists: " + employee.getEmail()
            );
        }

        if (!companyRepository.existsById(employee.getCompanyId())) {
            throw new EmployeeCompanyNotFoundException(
                    "Company not found with id: " + employee.getCompanyId()
            );
        }


        return employeeRepository.save(employee);
    }

    public List<Employee> getEmployeesByCompanyId(Long companyId) {
        return employeeRepository.findByCompanyId(companyId);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public Optional<Employee> updateEmployee(Long id, Employee updatedEmployee) {
        return employeeRepository.findById(id)
                .map(existingEmployee -> {
                    existingEmployee.setCompanyId(updatedEmployee.getCompanyId());
                    existingEmployee.setName(updatedEmployee.getName());
                    existingEmployee.setEmail(updatedEmployee.getEmail());
                    existingEmployee.setDesignation(updatedEmployee.getDesignation());

                    return employeeRepository.save(existingEmployee);
                });
    }

    public boolean deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            return false;
        }

        employeeRepository.deleteById(id);
        return true;
    }


}