package com.companyos.company;

import com.companyos.company.dto.CompanyResponse;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {

    public CompanyResponse toResponse(Company company) {
        return new CompanyResponse(
                company.getId(),
                company.getName(),
                company.getCreatedAt()
        );
    }
}