package com.agrocad.main.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class CompanyDTO {

    private Long id;
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotNull(message = "Property is mandatory")
    private Instant initialDate;
    @NotNull(message = "Property is mandatory")
    private Instant finalDate;
    @NotBlank(message = "CNPJ is mandatory")
    private String cnpj;
    @NotNull(message = "Property is mandatory")
    private PropertyInfoDTO propertyInfo;
    @NotNull(message = "Property is mandatory")
    private LaboratoryDTO laboratory;
    private String observation;
}
