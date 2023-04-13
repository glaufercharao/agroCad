package com.agrocad.main.services;

import com.agrocad.main.dto.LaboratoryDTO;
import com.agrocad.main.dto.PropertyInfoDTO;
import com.agrocad.main.entities.PropertyInfo;
import com.agrocad.main.mapper.Mappable;
import com.agrocad.main.repositories.PropertyInfoRepository;
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
public class PropertyInfoService implements Mappable {

    private final PropertyInfoRepository propertyInfoRepository;

    @Transactional
    public PropertyInfoDTO savePropertyInfo(PropertyInfoDTO propertyInfoDTO) {
        return map(propertyInfoRepository
                .save(map(propertyInfoDTO, PropertyInfo.class)), PropertyInfoDTO.class);
    }

    @Transactional
    public PropertyInfoDTO updatePropertyInfo(PropertyInfoDTO propertyInfoDTO) {
        Optional<PropertyInfo> propertyInfoTmp = propertyInfoRepository
                .findById(propertyInfoDTO.getId());
        if (!propertyInfoTmp.isPresent()) {
            throw new ResourceNotFoundException("Id not found");
        }
        return map(propertyInfoRepository.save(map(propertyInfoDTO, PropertyInfo.class)), PropertyInfoDTO.class);
    }

    @Transactional(readOnly = true)
    public PropertyInfoDTO findPropertyInfo(Long id){
        Optional<PropertyInfo> propertyInfoTmp =propertyInfoRepository.findById(id);
        if (!propertyInfoTmp.isPresent()) {
            throw new ResourceNotFoundException("Id not found "+ id);
        }
        return map(propertyInfoTmp, PropertyInfoDTO.class);
    }

    @Transactional(readOnly = true)
    public List<PropertyInfoDTO> findAllPropertyInfo(){
        return map(propertyInfoRepository.findAll(),PropertyInfoDTO.class);
    }

    public void deletePropertyInfo(Long id) {
        if(propertyInfoRepository.findById(id).isPresent()){
            try {
                propertyInfoRepository.deleteById(id);
            }catch (DataIntegrityViolationException e){
                throw  new DatabaseException("Integrity violation");
            }
        } else{
            throw new ResourceNotFoundException("Id not found "+id);
        }

    }

}

