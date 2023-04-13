package com.agrocad.main.dto;

import com.agrocad.main.entities.Laboratory;
import com.agrocad.main.entities.PropertyInfo;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.time.Instant;

@Data
public class CompanyDTO {
    private Long id;
    private String name;
    private Instant initialDate;
    private Instant finalDate;
    private String cnpj;
    private PropertyInfoDTO propertyInfo;
    private LaboratoryDTO laboratory;
    private String observation;
}
