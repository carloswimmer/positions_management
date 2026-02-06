package com.carloswimmer.positions_management.exceptions;

public class ConflictException extends RuntimeException {

    public ConflictException() {
        super("User already exists");
    }
    
}
