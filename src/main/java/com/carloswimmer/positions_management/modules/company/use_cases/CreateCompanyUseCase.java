package com.carloswimmer.positions_management.modules.company.use_cases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.carloswimmer.positions_management.exceptions.ConflictException;
import com.carloswimmer.positions_management.modules.company.entities.CompanyEntity;
import com.carloswimmer.positions_management.modules.company.repositories.CompanyRepository;

@Service
public class CreateCompanyUseCase {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CompanyEntity execute(CompanyEntity company) {
        this.companyRepository.findByUsernameOrEmail(company.getUsername(), company.getEmail())
                .ifPresent(entity -> {
                    throw new ConflictException("Company");
                });

        String encodedPassword = this.passwordEncoder.encode(company.getPassword());
        company.setPassword(encodedPassword);

        return this.companyRepository.save(company);
    }

}
