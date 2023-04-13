package com.agrocad.main.services;

import com.agrocad.main.dto.LaboratoryDTO;
import com.agrocad.main.entities.Laboratory;
import com.agrocad.main.mapper.Mappable;
import com.agrocad.main.repositories.LaboratoryRepository;
import com.agrocad.main.services.exceptions.DatabaseException;
import com.agrocad.main.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LaboratoryService implements Mappable {

    private final LaboratoryRepository laboratoryRepository;

    @Transactional
    public LaboratoryDTO saveLaboratory(LaboratoryDTO laboratoryDTO) {
        return map(laboratoryRepository
                .save(map(laboratoryDTO, Laboratory.class)), LaboratoryDTO.class);
    }

    @Transactional
    public LaboratoryDTO updateLaboratory(LaboratoryDTO laboratoryDTO) {
        Optional<Laboratory> companyTmp = laboratoryRepository
                .findById(laboratoryDTO.getId());
        if (!companyTmp.isPresent()) {
            throw new ResourceNotFoundException("Id not found");
        }
        return map(laboratoryRepository.save(map(laboratoryDTO, Laboratory.class)), LaboratoryDTO.class);
    }

    @Transactional(readOnly = true)
    public LaboratoryDTO findLaboratory(Long id){
        Optional<Laboratory> laboratoryTmp =laboratoryRepository.findById(id);
        if (!laboratoryTmp.isPresent()) {
            throw new ResourceNotFoundException("Id not found "+ id);
        }
        return map(laboratoryTmp,LaboratoryDTO.class);
    }

    @Transactional(readOnly = true)
    public List<LaboratoryDTO> findAllLaboratory(){
        return map(laboratoryRepository.findAll(),LaboratoryDTO.class);
    }


    public void deleteLaboratory(Long id) {
        if(laboratoryRepository.findById(id).isPresent()){
            try {
                laboratoryRepository.deleteById(id);
            }catch (DataIntegrityViolationException e){
                throw new DatabaseException("Integrity violation");
            }
        }else{
            throw new ResourceNotFoundException("Id not found "+id);
        }

    }

}

