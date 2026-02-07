package com.carloswimmer.positions_management.exceptions;

public class ConflictException extends RuntimeException {

    public ConflictException(String entityName) {
        super(entityName + " already exists");
    }
    
}
