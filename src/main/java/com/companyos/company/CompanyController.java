package com.companyos.company;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import jakarta.validation.Valid;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import com.companyos.company.dto.CompanyRequest;
import com.companyos.company.dto.CompanyResponse;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyService companyService;
    private final CompanyMapper companyMapper;

    public CompanyController(CompanyService companyService,
                             CompanyMapper companyMapper) {
        this.companyService = companyService;
        this.companyMapper = companyMapper;
    }

    @PostMapping
    public CompanyResponse createCompany(
            @Valid @RequestBody CompanyRequest request) {

        Company company = new Company(request.getName());

        Company savedCompany = companyService.createCompany(company);

        return companyMapper.toResponse(savedCompany);
    }
    @GetMapping
    public List<CompanyResponse> getAllCompanies() {

        return companyService.getAllCompanies()
                .stream()
                .map(companyMapper::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponse> getCompanyById(
            @PathVariable Long id) {

        Company company = companyService.getCompanyById(id);

        return ResponseEntity.ok(companyMapper.toResponse(company));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {

        boolean deleted = companyService.deleteCompany(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyResponse> updateCompany(
            @PathVariable Long id,
            @Valid @RequestBody CompanyRequest request) {

        Company updatedCompany = new Company(request.getName());

        return companyService.updateCompany(id, updatedCompany)
                .map(company -> ResponseEntity.ok(companyMapper.toResponse(company)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }




}