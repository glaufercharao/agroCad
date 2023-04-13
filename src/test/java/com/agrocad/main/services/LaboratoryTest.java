package com.agrocad.main.services;

import com.agrocad.main.dto.LaboratoryDTO;
import com.agrocad.main.entities.Laboratory;
import com.agrocad.main.mapper.Mappable;
import com.agrocad.main.repositories.LaboratoryRepository;
import com.agrocad.main.services.exceptions.DatabaseException;
import com.agrocad.main.services.exceptions.ResourceNotFoundException;
import com.agrocad.main.util.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;


@ExtendWith(SpringExtension.class)
public class LaboratoryTest implements Mappable {

    @InjectMocks
    private LaboratoryService service;

    @Mock
    private LaboratoryRepository repository;

    private long existingId;
    private long nonExistingId;
    private long dependentId;

    private Laboratory laboratory;

    @BeforeEach
    void setUp(){
        existingId = 1L;
        nonExistingId = 4L;
        dependentId = 1L;
        laboratory = Factory.createLaboratory();
        laboratory.setId(existingId);

        Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(laboratory));
        Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(laboratory);

        Mockito.doThrow(EmptyResultDataAccessException.class).when(repository).deleteById(nonExistingId);
        Mockito.doThrow(DataIntegrityViolationException.class).when(repository).deleteById(dependentId);
    }

    @Test
    public void findByIdShouldReturnLaboratory(){
        LaboratoryDTO result = service.findLaboratory(existingId);
        Assertions.assertNotNull(result);
        Assertions.assertEquals("Agro lab", result.getName());
    }

    @Test
    public void saveShouldReturnCompany(){
        LaboratoryDTO laboratoryResult = service.saveLaboratory(map(laboratory, LaboratoryDTO.class));

        Assertions.assertNotNull(laboratoryResult);
        Assertions.assertEquals("Agro lab", laboratoryResult.getName());
        Assertions.assertEquals(existingId, laboratoryResult.getId());
    }

    @Test
    public void deleteShouldThrowDatabaseExceptionWhenDependentId() {

        Assertions.assertThrows(DatabaseException.class, () -> {
            service.deleteLaboratory(dependentId);
        });
    }

    @Test
    public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.deleteLaboratory(nonExistingId);
        });
    }


}
