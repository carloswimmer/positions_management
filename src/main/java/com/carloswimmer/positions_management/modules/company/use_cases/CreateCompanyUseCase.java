package com.carloswimmer.positions_management.modules.company.use_cases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carloswimmer.positions_management.exceptions.ConflictException;
import com.carloswimmer.positions_management.modules.company.entities.CompanyEntity;
import com.carloswimmer.positions_management.modules.company.entities.repositories.CompanyRepository;

@Service
public class CreateCompanyUseCase {
    
    @Autowired
    private CompanyRepository companyRepository;

    public CompanyEntity execute(CompanyEntity company) {
        this.companyRepository.findByUsernameOrEmail(company.getUsername(), company.getEmail())
        .ifPresent(entity -> {
            throw new ConflictException("Company");
        });
    
    return this.companyRepository.save(company);
    }

}
