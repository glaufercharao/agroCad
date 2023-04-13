package com.agrocad.main.services;

import com.agrocad.main.dto.CompanyDTO;
import com.agrocad.main.entities.Company;
import com.agrocad.main.mapper.Mappable;
import com.agrocad.main.repositories.CompanyRepository;
import lombok.RequiredArgsConstructor;
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
            throw new RuntimeException();
        }
        return map(companyRepository.save(map(companyDTO, Company.class)), CompanyDTO.class);
    }

    @Transactional(readOnly = true)
    public CompanyDTO findCompany(Long id){
        Optional<Company> companyTmp =companyRepository.findById(id);
        if (!companyTmp.isPresent()) {
            throw new RuntimeException();
        }
        return map(companyTmp,CompanyDTO.class);
    }

    @Transactional
    public boolean deleteCompany(Long id) {
        Optional<Company> companyTmp = companyRepository.findById(id);

        if (!companyTmp.isPresent()) {
            throw new RuntimeException();
        }
        companyRepository.deleteById(id);
        return true;
    }

}

