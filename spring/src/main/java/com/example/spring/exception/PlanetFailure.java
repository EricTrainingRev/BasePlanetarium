package com.example.spring.exception;

public class PlanetFailure extends RuntimeException {
    public PlanetFailure(String message){
        super(message);
    }
    
}
