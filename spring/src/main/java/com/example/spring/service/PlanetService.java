package com.example.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.entities.Planet;
import com.example.spring.exception.PlanetFailure;
import com.example.spring.repository.PlanetDao;

@Service
public class PlanetService {

    /*
     * findAllPlanets
     * findPlanetByName
     * findPlanetById
     * createPlanet
     * deletePlanet
     */

    @Autowired
    private PlanetDao planetDao;

    public List<Planet> findAllPlanets(){
        List<Planet> planets = this.planetDao.findAll();
        if(planets.size() != 0){
            return planets;
        } else {
            throw new PlanetFailure("no planets found");
        }
    }

    public Planet findPlanetByName(String name){
        Optional<Planet> possiblePlanet = this.planetDao.findByName(name);
        if(possiblePlanet.isPresent()){
            return possiblePlanet.get();
        } else {
            throw new PlanetFailure("Planet not found");
        }
    }

    public Planet findPlanetById(int id){
        Optional<Planet> possiblePlanet = this.planetDao.findById(id);
        if(possiblePlanet.isPresent()){
            return possiblePlanet.get();
        } else {
            throw new PlanetFailure("Planet not found");
        }
    }

    public String createPlanet(Planet planet){
        try {
            this.planetDao.createPlanet(planet.getName(), planet.getOwnerId());
            return "planet added successfully";
        } catch (Exception e) { // make more specific
            throw new PlanetFailure("could not add planet");
        }
    }

    public String deletePlanet(int id){
        try {
            this.planetDao.deleteById(id);
            return "Planet Deleted";
        } catch (Exception e) { // make more specific
            throw new PlanetFailure("could not delete planet");
        }
    }


    
}
