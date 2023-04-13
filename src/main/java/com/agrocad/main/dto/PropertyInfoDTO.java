package com.agrocad.main.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PropertyInfoDTO {
    private Long id;
    @NotBlank(message = "Name is mandatory")
    private String name;
}
