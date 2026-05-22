package com.carloswimmer.positions_management.modules.company.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carloswimmer.positions_management.modules.company.dtos.CreateJobDTO;
import com.carloswimmer.positions_management.modules.company.entities.JobEntity;
import com.carloswimmer.positions_management.modules.company.use_cases.CreateJobUseCase;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private CreateJobUseCase createJobUseCase;

    @PostMapping("/")
    public JobEntity create(@Valid @RequestBody CreateJobDTO jobDTO, HttpServletRequest request) {
        var companyId = request.getAttribute("companyId");

        JobEntity job = JobEntity.builder()
                .benefits(jobDTO.getBenefits())
                .companyId(UUID.fromString(companyId.toString()))
                .description(jobDTO.getDescription())
                .level(jobDTO.getLevel())
                .build();

        return this.createJobUseCase.execute(job);
    }

}
