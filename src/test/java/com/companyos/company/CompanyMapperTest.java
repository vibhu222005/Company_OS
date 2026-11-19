package com.companyos.company;

import com.companyos.company.dto.CompanyResponse;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CompanyMapperTest {

    @Test
    void shouldMapCompanyToResponse() {

        LocalDateTime createdAt = LocalDateTime.now();

        Company company = new Company("TCS");
        company.setId(1L);
        company.setCreatedAt(createdAt);

        CompanyMapper mapper = new CompanyMapper();

        CompanyResponse response = mapper.toResponse(company);

        assertEquals(1L, response.getId());
        assertEquals("TCS", response.getName());
        assertEquals(createdAt, response.getCreatedAt());
    }
}