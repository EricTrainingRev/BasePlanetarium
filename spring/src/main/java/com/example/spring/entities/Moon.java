package com.example.spring.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Moon {
    
    @Id
    private int id;
    private String name;
    private int myPlanetId;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getMyPlanetId() {
        return myPlanetId;
    }
    public void setMyPlanetId(int myPlanetId) {
        this.myPlanetId = myPlanetId;
    }
    @Override
    public String toString() {
        return "Moon [id=" + id + ", name=" + name + ", myPlanetId=" + myPlanetId + "]";
    }

    

    

}
