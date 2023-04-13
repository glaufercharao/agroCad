package com.agrocad.main.resources;

import com.agrocad.main.dto.CompanyDTO;
import com.agrocad.main.services.CompanyService;
import com.agrocad.main.services.exceptions.DatabaseException;
import com.agrocad.main.services.exceptions.ResourceNotFoundException;
import com.agrocad.main.util.Factory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CompanyResource.class)
public class CompanyResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompanyService service;

    @Autowired
    private ObjectMapper objectMapper;

    private Long existingId;
    private Long nonExistingId;
    private Long dependentId;

    private CompanyDTO company;

    @BeforeEach
    void setUp(){
        existingId = 1L;
        nonExistingId = 2L;
        dependentId = 3L;

        company = Factory.createCompanyDTO();
        company.setId(existingId);

        when(service.saveCompany(any())).thenReturn(company);
        when(service.findCompany(existingId)).thenReturn(company);
        when(service.updateCompany(company)).thenReturn(company);

        doThrow(ResourceNotFoundException.class).when(service).deleteCompany(nonExistingId);
        doThrow(DatabaseException.class).when(service).deleteCompany(dependentId);

    }

    @Test
    public void saveShouldReturnCompanyDTOCreated() throws Exception {

        String jsonBody = objectMapper.writeValueAsString(company);

        ResultActions result =
                mockMvc.perform(post("/company")
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isCreated());
        result.andExpect(jsonPath("$.id").exists());
        result.andExpect(jsonPath("$.name").exists());
        result.andExpect(jsonPath("$.cnpj").exists());
    }

    @Test
    public void updateShouldReturnCompanyDTOWhenIdExists() throws Exception {

        String jsonBody = objectMapper.writeValueAsString(company);

        String expectedName = company.getName();
        String expectedCNPJ = company.getCnpj();

        ResultActions result =
                mockMvc.perform(put("/company")
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.id").value(existingId));
        result.andExpect(jsonPath("$.name").value(expectedName));
        result.andExpect(jsonPath("$.cnpj").value(expectedCNPJ));
    }

    @Test
    public void deleteShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {

        ResultActions result =
                mockMvc.perform(delete("/company/{id}", nonExistingId)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isNotFound());
    }

    @Test
    public void deleteShouldReturnNoContentWhenIdExists() throws Exception {

        ResultActions result =
                mockMvc.perform(delete("/company/{id}", existingId)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isNoContent());
    }
}
