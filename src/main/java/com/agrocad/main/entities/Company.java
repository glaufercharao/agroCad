package com.agrocad.main.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "tb_company")
@Data
public class Company implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Instant initialDate;
    private Instant finalDate;
    private String cnpj;
    @OneToOne
    @JoinColumn(name = "property_info_id")
    private PropertyInfo propertyInfo;
    @OneToOne
    @JoinColumn(name = "laboratory_id")
    private Laboratory laboratory;
    private String observation;
}
