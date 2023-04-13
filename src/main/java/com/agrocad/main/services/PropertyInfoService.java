package com.agrocad.main.services;

import com.agrocad.main.dto.LaboratoryDTO;
import com.agrocad.main.dto.PropertyInfoDTO;
import com.agrocad.main.entities.PropertyInfo;
import com.agrocad.main.mapper.Mappable;
import com.agrocad.main.repositories.PropertyInfoRepository;
import lombok.RequiredArgsConstructor;
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
            throw new RuntimeException();
        }
        return map(propertyInfoRepository.save(map(propertyInfoDTO, PropertyInfo.class)), PropertyInfoDTO.class);
    }

    @Transactional(readOnly = true)
    public PropertyInfoDTO findPropertyInfo(Long id){
        Optional<PropertyInfo> propertyInfoTmp =propertyInfoRepository.findById(id);
        if (!propertyInfoTmp.isPresent()) {
            throw new RuntimeException();
        }
        return map(propertyInfoTmp, PropertyInfoDTO.class);
    }

    @Transactional(readOnly = true)
    public List<PropertyInfoDTO> findAllPropertyInfo(){
        return map(propertyInfoRepository.findAll(),PropertyInfoDTO.class);
    }

    @Transactional
    public boolean deletePropertyInfo(Long id) {
        Optional<PropertyInfo> propertyInfoTmp = propertyInfoRepository.findById(id);

        if (!propertyInfoTmp.isPresent()) {
            throw new RuntimeException();
        }
        propertyInfoRepository.deleteById(id);
        return true;
    }

}

