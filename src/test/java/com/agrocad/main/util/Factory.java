package com.agrocad.main.util;

import com.agrocad.main.dto.CompanyDTO;
import com.agrocad.main.dto.LaboratoryDTO;
import com.agrocad.main.dto.PropertyInfoDTO;
import com.agrocad.main.entities.Company;
import com.agrocad.main.entities.Laboratory;
import com.agrocad.main.entities.PropertyInfo;

import java.time.Instant;


public class Factory {
    public static Company createCompany(){
        return Company.builder()
                .name("Agrotis cia LTDA")
                .cnpj("40.876.523/0001-10")
                .initialDate(Instant.parse("2023-04-13T19:34:50.63Z"))
                .finalDate(Instant.parse("2023-04-30T07:45:29.10Z"))
                .propertyInfo(PropertyInfo.builder().id(1L).name("Teste Propriedade").build())
                .laboratory(Laboratory.builder().id(1L).name("Teste Laboratorio").build())
                .observation("Objeto construido com sucesso").build();
    }

    public static Laboratory createLaboratory(){
        return Laboratory.builder().name("Agro lab").build();
    }

    public static CompanyDTO createCompanyDTO(){
        return CompanyDTO.builder()
                .name("Agrotis cia LTDA")
                .cnpj("40.876.523/0001-10")
                .initialDate(Instant.parse("2023-04-13T19:34:50.63Z"))
                .finalDate(Instant.parse("2023-04-30T07:45:29.10Z"))
                .propertyInfo(PropertyInfoDTO.builder().id(1L).name("Teste Propriedade").build())
                .laboratory(LaboratoryDTO.builder().id(1L).name("Teste Laboratorio").build())
                .observation("Objeto construido com sucesso").build();
    }
}
