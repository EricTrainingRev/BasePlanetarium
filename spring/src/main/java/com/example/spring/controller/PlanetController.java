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

import com.example.spring.entities.Planet;
import com.example.spring.exception.AccountFailure;
import com.example.spring.exception.PlanetFailure;
import com.example.spring.service.PlanetService;


@RestController
public class PlanetController {

    @Autowired
    private PlanetService planetService;

    /*
     * findAllPlanets
     * findPlanetByName
     * findPlanetById
     * createPlanet
     * deletePlanet
     */

    @ExceptionHandler(PlanetFailure.class)
    public ResponseEntity<String> planetFailureHandler(PlanetFailure e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccountFailure.class)
    public ResponseEntity<String> sessionNotValidated(AccountFailure e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/api/planet")
    public ResponseEntity<List<Planet>> findAllPlanets(){
        return new ResponseEntity<>(this.planetService.findAllPlanets(), HttpStatus.OK);
    }

    @GetMapping("/api/planet/{name}")
    public ResponseEntity<Planet> findPlanetByName(@PathVariable String name){
        return new ResponseEntity<>(this.planetService.findPlanetByName(name), HttpStatus.OK);
    }

    @GetMapping("/api/planet/id/{id}")
    public ResponseEntity<Planet> findPlanetById(@PathVariable int id){
        return new ResponseEntity<>(this.planetService.findPlanetById(id), HttpStatus.OK);
    }

    @PostMapping("/api/planet")
    public ResponseEntity<String> createPlanet(@RequestBody Planet planet){
        return new ResponseEntity<>(this.planetService.createPlanet(planet), HttpStatus.CREATED);
    }

    @DeleteMapping("/api/planet/{id}")
    public ResponseEntity<String> deletePlanet(@PathVariable int id){
        return new ResponseEntity<>(this.planetService.deletePlanet(id), HttpStatus.OK);
    }
    
}
