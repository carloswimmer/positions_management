package com.carloswimmer.positions_management.modules.candidate.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carloswimmer.positions_management.modules.candidate.CandidateEntity;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidates")
public class CandidateController {
    

    @PostMapping("/")
    public void create(@Valid  @RequestBody CandidateEntity candidate) {
        System.out.println("Candidate created: " + candidate);
    }

}
