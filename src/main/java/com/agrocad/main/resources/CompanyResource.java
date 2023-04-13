package com.agrocad.main.resources;

import com.agrocad.main.dto.CompanyDTO;
import com.agrocad.main.services.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyResource {

    private final CompanyService service;

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDTO> findCompany(@PathVariable Long id){
       return ResponseEntity.ok().body(service.findCompany(id));
    }

    @PostMapping
    public ResponseEntity<CompanyDTO> saveCompany(@RequestBody CompanyDTO companyDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveCompany(companyDTO));
    }

    @PutMapping
    public ResponseEntity<CompanyDTO> updateCompany(@RequestBody CompanyDTO companyDTO){
        return ResponseEntity.status(HttpStatus.OK).body(service.updateCompany(companyDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCompany(@PathVariable Long id){
        return service.deleteCompany(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
