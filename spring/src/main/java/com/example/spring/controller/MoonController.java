package com.example.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.entities.Moon;
import com.example.spring.exception.AccountFailure;
import com.example.spring.exception.MoonFailure;
import com.example.spring.service.MoonService;

@RestController
public class MoonController {

    @Autowired
    private MoonService moonService;

    @ExceptionHandler(MoonFailure.class)
    public ResponseEntity<String> moonFailureHandler(MoonFailure e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccountFailure.class)
    public ResponseEntity<String> sessionNotValidated(AccountFailure e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/api/moon")
    public ResponseEntity<List<Moon>> findAllmoons(){
        return new ResponseEntity<>(this.moonService.findAllMoons(), HttpStatus.OK);
    }

    @GetMapping("/api/moon/{name}")
    public ResponseEntity<Moon> findmoonByName(@PathVariable String name){
        return new ResponseEntity<>(this.moonService.findMoonByName(name), HttpStatus.OK);
    }

    @GetMapping("/api/moon/id/{id}")
    public ResponseEntity<Moon> findmoonById(@PathVariable int id){
        return new ResponseEntity<>(this.moonService.findMoonById(id), HttpStatus.OK);
    }

    @PostMapping("/api/moon")
    public ResponseEntity<String> createmoon(@RequestBody Moon moon){
        return new ResponseEntity<>(this.moonService.createMoon(moon), HttpStatus.CREATED);
    }

    @DeleteMapping("/api/moon/{id}")
    public ResponseEntity<String> deletemoon(@PathVariable int id){
        return new ResponseEntity<>(this.moonService.deleteMoon(id), HttpStatus.OK);
    }
    
}
