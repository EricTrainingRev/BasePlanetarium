package com.example.spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring.entities.Moon;

public interface MoonDao extends JpaRepository<Moon, Integer>{

    /*
     * findAll
     * findByName
     * findById
     * createMoon
     * deleteMoon
     */

     Optional<Moon> findByName(String name);

     @Transactional
     @Modifying
     @Query(value = "insert into moons values (default, :name , :myPlanetId)", nativeQuery = true)
     void createMoon(@Param("name") String name, @Param("myPlanetId") int myPlanetId);
 
     @Transactional
     @Modifying
     @Query(value = "delete from moons where id = :id", nativeQuery = true)
     void deleteMoon(int id);
    
}
