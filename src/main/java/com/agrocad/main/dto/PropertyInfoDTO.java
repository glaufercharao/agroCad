package com.agrocad.main.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PropertyInfoDTO {
    private Long id;
    @NotBlank(message = "Name is mandatory")
    private String name;
}
