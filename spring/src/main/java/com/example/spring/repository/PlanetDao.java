package com.example.spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring.entities.Planet;

public interface PlanetDao extends JpaRepository<Planet,Integer> {

    /*
     * findAll
     * findByName
     * findById
     * createPlanet
     * deletePlanet
     */

    Optional<Planet> findByName(String name);

    @Transactional
    @Modifying
    @Query(value = "insert into planets values (default, :name , :ownerId)", nativeQuery = true)
    void createPlanet(@Param("name") String name, @Param("ownerId") int ownerId);

    @Transactional
    @Modifying
    @Query(value = "delete from planets where id = :id", nativeQuery = true)
    void deletePlanet(int id);
    
}
