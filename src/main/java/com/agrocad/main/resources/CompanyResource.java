package com.agrocad.main.resources;

import com.agrocad.main.dto.CompanyDTO;
import com.agrocad.main.services.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyResource {

    private final CompanyService service;

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDTO> findCompany(@PathVariable Long id){
       return ResponseEntity.ok().body(service.findCompany(id));
    }
}
