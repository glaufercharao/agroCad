package com.agrocad.main.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "tb_property_info")
@Data
public class PropertyInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotBlank(message = "ID is mandatory")
    private Long id;
    @NotBlank(message = "Name is mandatory")
    private String name;
}
