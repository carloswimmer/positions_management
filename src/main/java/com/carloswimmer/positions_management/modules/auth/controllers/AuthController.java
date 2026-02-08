package com.carloswimmer.positions_management.modules.auth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carloswimmer.positions_management.modules.auth.dtos.AuthCompanyDTO;
import com.carloswimmer.positions_management.modules.auth.use_cases.AuthCompanyUseCase;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthCompanyUseCase authCompanyUseCase;

    @PostMapping("/company")
    public ResponseEntity<Object> createToken(@RequestBody AuthCompanyDTO authCompanyDTO)
            throws AuthenticationException {
        try {
            String token = this.authCompanyUseCase.execute(authCompanyDTO);
            return ResponseEntity.ok().body(token);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
        }
    }

}
