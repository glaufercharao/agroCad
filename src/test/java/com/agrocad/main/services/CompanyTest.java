package com.agrocad.main.services;


import com.agrocad.main.dto.CompanyDTO;
import com.agrocad.main.entities.Company;
import com.agrocad.main.mapper.Mappable;
import com.agrocad.main.repositories.CompanyRepository;
import com.agrocad.main.services.exceptions.ResourceNotFoundException;
import com.agrocad.main.util.Factory;
import lombok.Builder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.Mockito.times;


@ExtendWith(SpringExtension.class)
public class CompanyTest implements Mappable {

    @InjectMocks
    private CompanyService service;

    @Mock
    private CompanyRepository repository;

    private long existingId;
    private long nonExistingId;
    private long dependentId;
    private Company company;

    @BeforeEach
    void setUp(){
        existingId = 1L;
        nonExistingId = 4L;
        company = Factory.createCompany();
        company.setId(existingId);

        Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(company));
        Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(company);
    }

    @Test
    public void findByIdShouldReturnCompany(){
        CompanyDTO result = service.findCompany(existingId);
        Assertions.assertNotNull(result);
        Assertions.assertEquals("40.876.523/0001-10", result.getCnpj());
    }

    @Test
    public void saveShouldReturnCompany(){
        CompanyDTO companyResult = service.saveCompany(map(company, CompanyDTO.class));

        Assertions.assertNotNull(companyResult);
        Assertions.assertEquals("Agrotis cia LTDA", companyResult.getName());
    }

    @Test
    public void deleteShouldDoNothingWhenIdExists() {

        Assertions.assertDoesNotThrow(() -> {
            service.deleteCompany(existingId);
        });
    }

    @Test
    public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.deleteCompany(nonExistingId);
        });
    }
}
