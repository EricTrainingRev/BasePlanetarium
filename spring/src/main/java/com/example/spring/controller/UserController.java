package com.example.spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.entities.Authentication;
import com.example.spring.entities.User;
import com.example.spring.exception.AccountFailure;
import com.example.spring.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @ExceptionHandler(AccountFailure.class)
    public ResponseEntity<String> accountProblem(AccountFailure e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); // generic response for now
    }

    @PostMapping("/register")
    public ResponseEntity<String> createAccount(@RequestBody User user){
        return new ResponseEntity<>(this.userService.register(user),HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Authentication credentials, HttpSession session){
        session.setAttribute("user", this.userService.authenticate(credentials));
        return new ResponseEntity<>("login successful",HttpStatus.ACCEPTED);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session){
        session.invalidate();
        return new ResponseEntity<>("Logged out successfully", HttpStatus.OK);
    }
    
}
