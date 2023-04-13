package com.agrocad.main.services;

import com.agrocad.main.dto.CompanyDTO;
import com.agrocad.main.entities.Company;
import com.agrocad.main.mapper.Mappable;
import com.agrocad.main.repositories.CompanyRepository;
import com.agrocad.main.services.exceptions.DatabaseException;
import com.agrocad.main.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService implements Mappable {

    private final CompanyRepository companyRepository;

    @Transactional
    public CompanyDTO saveCompany(CompanyDTO companyDTO) {
        return map(companyRepository
                .save(map(companyDTO, Company.class)), CompanyDTO.class);
    }

    @Transactional
    public CompanyDTO updateCompany(CompanyDTO companyDTO) {
        Optional<Company> companyTmp = companyRepository
                .findById(companyDTO.getId());
        if (!companyTmp.isPresent()) {
            throw new ResourceNotFoundException("Id not found");
        }
        return map(companyRepository.save(map(companyDTO, Company.class)), CompanyDTO.class);
    }

    @Transactional(readOnly = true)
    public CompanyDTO findCompany(Long id){
        Optional<Company> companyTmp =companyRepository.findById(id);
        if (!companyTmp.isPresent()) {
            throw new ResourceNotFoundException("Id not found "+ id);
        }
        return map(companyTmp,CompanyDTO.class);
    }

    public void deleteCompany(Long id) {
        if(companyRepository.findById(id).isPresent() ){
            try {
                companyRepository.deleteById(id);
            }catch (DataIntegrityViolationException e){
                throw new DatabaseException("Integrity violation");
            }
        }else{
            throw new ResourceNotFoundException("Id not found "+id);
        }

    }

}

