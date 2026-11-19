package com.companyos.company;

import com.companyos.exception.CompanyNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private CompanyService companyService;

    @Test
    void shouldCreateCompany() {
        Company company = new Company("Infosys");

        when(companyRepository.save(company))
                .thenReturn(company);

        Company result = companyService.createCompany(company);

        assertEquals("Infosys", result.getName());
        verify(companyRepository).save(company);
    }

    @Test
    void shouldReturnFalseWhenDeletingMissingCompany() {
        when(companyRepository.existsById(9999L))
                .thenReturn(false);

        boolean result = companyService.deleteCompany(9999L);

        assertEquals(false, result);

        verify(companyRepository, org.mockito.Mockito.never())
                .deleteById(9999L);
    }

    @Test
    void shouldGetAllCompanies() {
        Company company1 = new Company("TCS");
        Company company2 = new Company("Infosys");

        when(companyRepository.findAll())
                .thenReturn(java.util.List.of(company1, company2));

        java.util.List<Company> result =
                companyService.getAllCompanies();

        assertEquals(2, result.size());
        assertEquals("TCS", result.get(0).getName());
        assertEquals("Infosys", result.get(1).getName());
    }

    @Test
    void shouldUpdateCompany() {
        Company existingCompany = new Company("Old Name");
        existingCompany.setId(1L);

        Company updatedCompany = new Company("New Name");

        when(companyRepository.findById(1L))
                .thenReturn(Optional.of(existingCompany));

        when(companyRepository.save(existingCompany))
                .thenReturn(existingCompany);

        Optional<Company> result =
                companyService.updateCompany(1L, updatedCompany);

        assertEquals(true, result.isPresent());
        assertEquals("New Name", result.get().getName());
    }

    @Test
    void shouldDeleteCompany() {
        when(companyRepository.existsById(1L))
                .thenReturn(true);

        boolean result = companyService.deleteCompany(1L);

        assertEquals(true, result);
        verify(companyRepository).deleteById(1L);
    }

    @Test
    void shouldGetCompanyById() {
        Company company = new Company("TCS");
        company.setId(1L);

        when(companyRepository.findById(1L))
                .thenReturn(Optional.of(company));

        Company result = companyService.getCompanyById(1L);

        assertEquals(1L, result.getId());
        assertEquals("TCS", result.getName());
    }

    @Test
    void shouldThrowExceptionWhenCompanyNotFound() {
        when(companyRepository.findById(9999L))
                .thenReturn(Optional.empty());

        assertThrows(
                CompanyNotFoundException.class,
                () -> companyService.getCompanyById(9999L)
        );
    }
}