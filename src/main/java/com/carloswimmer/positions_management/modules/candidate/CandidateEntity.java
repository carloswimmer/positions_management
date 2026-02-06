package com.carloswimmer.positions_management.modules.candidate;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CandidateEntity {
    
    private UUID id;
    private String name;

    @Pattern(regexp = "\\S+", message = "Username cannot have empty spaces")
    private String username;

    @Email(message = "Invalid email address")
    private String email;

    @Length(min = 8, max = 100, message = "Password must be between 8 and 100 characters")
    private String password;
    private String description;
    private String curriculum;
}
