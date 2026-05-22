package com.carloswimmer.positions_management.modules.candidate.use_cases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.carloswimmer.positions_management.exceptions.ConflictException;
import com.carloswimmer.positions_management.modules.candidate.entities.CandidateEntity;
import com.carloswimmer.positions_management.modules.candidate.repositories.CandidateRepository;

@Service
public class CreateCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CandidateEntity execute(CandidateEntity candidate) {
        this.candidateRepository
                .findByUsernameOrEmail(candidate.getUsername(), candidate.getEmail())
                .ifPresent(entity -> {
                    throw new ConflictException("Candidate");
                });

        var password = passwordEncoder.encode(candidate.getPassword());
        candidate.setPassword(password);

        return this.candidateRepository.save(candidate);
    }

}
