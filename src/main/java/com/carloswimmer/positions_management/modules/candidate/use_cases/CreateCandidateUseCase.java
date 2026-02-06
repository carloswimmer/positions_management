package com.carloswimmer.positions_management.modules.candidate.use_cases;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carloswimmer.positions_management.exceptions.ConflictException;
import com.carloswimmer.positions_management.modules.candidate.CandidateEntity;
import com.carloswimmer.positions_management.modules.candidate.CandidateRepository;

@Service
public class CreateCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public CandidateEntity execute(CandidateEntity candidate) {
        this.candidateRepository
        .findByUsernameOrEmail(candidate.getUsername(), candidate.getEmail())
        .ifPresent(entity -> {
            throw new ConflictException();
        });

        return this.candidateRepository.save(candidate);
    }

}
