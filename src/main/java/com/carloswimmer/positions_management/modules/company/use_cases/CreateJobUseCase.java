package com.carloswimmer.positions_management.modules.company.use_cases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carloswimmer.positions_management.modules.company.entities.JobEntity;
import com.carloswimmer.positions_management.modules.company.repositories.JobRepository;

@Service
public class CreateJobUseCase {

    @Autowired
    private JobRepository jobRepository;

    public JobEntity execute(JobEntity job) {
        return this.jobRepository.save(job);
    }

}
