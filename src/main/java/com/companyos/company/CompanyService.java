package com.companyos.company;

import org.springframework.stereotype.Service;
import com.companyos.exception.CompanyNotFoundException;
import java.util.List;

import java.util.Optional;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompanyById(Long id) {

        return companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException(id));
    }

    public Optional<Company> updateCompany(Long id, Company updatedCompany) {

        return companyRepository.findById(id)
                .map(existingCompany -> {
                    existingCompany.setName(updatedCompany.getName());
                    return companyRepository.save(existingCompany);
                });
    }

    public boolean deleteCompany(Long id) {

        if (!companyRepository.existsById(id)) {
            return false;
        }

        companyRepository.deleteById(id);
        return true;
    }

}