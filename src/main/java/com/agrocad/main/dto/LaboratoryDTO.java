package com.agrocad.main.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LaboratoryDTO {
    private Long id;
    @NotBlank(message = "Name is mandatory")
    private String name;
}
