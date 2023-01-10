package com.example.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.entities.Moon;
import com.example.spring.exception.MoonFailure;
import com.example.spring.repository.MoonDao;

@Service
public class MoonService {

    /*
     * findAllMoons
     * findMoonByName
     * findMoonById
     * createMoon
     * deleteMoon
     */

    @Autowired
    private MoonDao moonDao;

    public List<Moon> findAllMoons(){
        List<Moon> moons = this.moonDao.findAll();
        if(moons.size() != 0){
            return moons;
        } else {
            throw new MoonFailure("No moons were found");
        }
    }

    public Moon findMoonById(int id){
        Optional<Moon> possibleMoon = this.moonDao.findById(id);
        if(possibleMoon.isPresent()){
            return possibleMoon.get();
        } else {
            throw new MoonFailure("Moon not found");
        }
    }

    public Moon findMoonByName(String name){
        Optional<Moon> possibleMoon = this.moonDao.findByName(name);
        if(possibleMoon.isPresent()){
            return possibleMoon.get();
        } else {
            throw new MoonFailure("Moon not found");
        }
    }

    public String createMoon(Moon moon){
        this.moonDao.createMoon(moon.getName(), moon.getMyPlanetId());
        return "Moon created";
    }

    public String deleteMoon(int id){
        this.moonDao.deleteMoon(id);
        return "Moon deleted";
    }


    
}
