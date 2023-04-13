package com.agrocad.main.resources;

import com.agrocad.main.dto.LaboratoryDTO;
import com.agrocad.main.services.LaboratoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/laboratory")
@RequiredArgsConstructor
public class LaboratoryResource {

    private final LaboratoryService service;

    @GetMapping
    public ResponseEntity<List<LaboratoryDTO>> findAllLaboratory(){
        return ResponseEntity.ok().body(service.findAllLaboratory());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LaboratoryDTO> findLaboratory(@PathVariable Long id){
       return ResponseEntity.ok().body(service.findLaboratory(id));
    }

    @PostMapping
    public ResponseEntity<LaboratoryDTO> saveLaboratory(@RequestBody LaboratoryDTO laboratoryDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveLaboratory(laboratoryDTO));
    }

    @PutMapping
    public ResponseEntity<LaboratoryDTO> updateLaboratory(@RequestBody LaboratoryDTO laboratoryDTO){
        return ResponseEntity.status(HttpStatus.OK).body(service.updateLaboratory(laboratoryDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteLaboratory(@PathVariable Long id){
        return service.deleteLaboratory(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
