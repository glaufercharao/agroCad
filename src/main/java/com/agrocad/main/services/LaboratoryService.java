package com.agrocad.main.services;

import com.agrocad.main.dto.LaboratoryDTO;
import com.agrocad.main.entities.Laboratory;
import com.agrocad.main.mapper.Mappable;
import com.agrocad.main.repositories.LaboratoryRepository;
import lombok.RequiredArgsConstructor;
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
            throw new RuntimeException();
        }
        return map(laboratoryRepository.save(map(laboratoryDTO, Laboratory.class)), LaboratoryDTO.class);
    }

    @Transactional(readOnly = true)
    public LaboratoryDTO findLaboratory(Long id){
        Optional<Laboratory> laboratoryTmp =laboratoryRepository.findById(id);
        if (!laboratoryTmp.isPresent()) {
            throw new RuntimeException();
        }
        return map(laboratoryTmp,LaboratoryDTO.class);
    }

    @Transactional(readOnly = true)
    public List<LaboratoryDTO> findAllLaboratory(){
        return map(laboratoryRepository.findAll(),LaboratoryDTO.class);
    }

    @Transactional
    public boolean deleteLaboratory(Long id) {
        Optional<Laboratory> laboratoryTmp = laboratoryRepository.findById(id);

        if (!laboratoryTmp.isPresent()) {
            throw new RuntimeException();
        }
        laboratoryRepository.deleteById(id);
        return true;
    }

}

