package com.agrocad.main.resources;

import com.agrocad.main.dto.PropertyInfoDTO;
import com.agrocad.main.services.PropertyInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/property")
@RequiredArgsConstructor
public class PropertyInfoResource {

    private final PropertyInfoService service;

    @GetMapping
    public ResponseEntity<List<PropertyInfoDTO>> findAllProperty(){
        return ResponseEntity.ok().body(service.findAllPropertyInfo());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropertyInfoDTO> findProperty(@PathVariable Long id){
       return ResponseEntity.ok().body(service.findPropertyInfo(id));
    }

    @PostMapping
    public ResponseEntity<PropertyInfoDTO> saveProperty(@RequestBody PropertyInfoDTO propertyInfoDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.savePropertyInfo(propertyInfoDTO));
    }

    @PutMapping
    public ResponseEntity<PropertyInfoDTO> updateProperty(@RequestBody PropertyInfoDTO propertyInfoDTO){
        return ResponseEntity.status(HttpStatus.OK).body(service.updatePropertyInfo(propertyInfoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProperty(@PathVariable Long id){
        return service.deletePropertyInfo(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
