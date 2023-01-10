package com.example.spring.exception;

/*
 * Generic Exception for account issues
 */

public class AccountFailure extends RuntimeException {
    public AccountFailure(String message){
        super(message);
    }
    
}
