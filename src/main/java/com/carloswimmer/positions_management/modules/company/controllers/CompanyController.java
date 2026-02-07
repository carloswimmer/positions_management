package com.carloswimmer.positions_management.modules.company.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carloswimmer.positions_management.modules.company.entities.CompanyEntity;
import com.carloswimmer.positions_management.modules.company.use_cases.CreateCompanyUseCase;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CreateCompanyUseCase createCompanyUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CompanyEntity company) {
        try {
            CompanyEntity result = createCompanyUseCase.execute(company);
            return ResponseEntity.ok().body(result);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

}
