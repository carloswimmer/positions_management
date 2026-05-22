package com.carloswimmer.positions_management.modules.auth.use_cases;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.carloswimmer.positions_management.modules.auth.dtos.AuthCandidateRequestDTO;
import com.carloswimmer.positions_management.modules.auth.dtos.AuthCandidateResponseDTO;
import com.carloswimmer.positions_management.modules.candidate.repositories.CandidateRepository;

@Service
public class AuthCandidateUseCase {

  @Value("${security.token.secret.candidate}")
  private String secretKey;

  @Autowired
  CandidateRepository candidateRepository;

  @Autowired
  PasswordEncoder passwordEncoder;

  public AuthCandidateResponseDTO execute(AuthCandidateRequestDTO authCandidateRequestDTO)
      throws AuthenticationException {
    var candidate = candidateRepository.findByUsername(authCandidateRequestDTO.username()).orElseThrow(() -> {
      throw new UsernameNotFoundException("Username or password incorrect");
    });

    var passwordMatches = passwordEncoder.matches(authCandidateRequestDTO.password(), candidate.getPassword());

    if (!passwordMatches) {
      throw new AuthenticationException();
    }

    Algorithm algorithm = Algorithm.HMAC256(secretKey);
    var token = JWT.create()
        .withIssuer("positions_management")
        .withSubject(candidate.getId().toString())
        .withClaim("roles", Arrays.asList("candidate"))
        .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
        .sign(algorithm);

    AuthCandidateResponseDTO authCandidateResponseDTO = AuthCandidateResponseDTO.builder()
        .acess_token(token).build();

    return authCandidateResponseDTO;
  }
}
