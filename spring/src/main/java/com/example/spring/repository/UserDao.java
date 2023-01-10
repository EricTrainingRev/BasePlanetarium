package com.example.spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring.entities.User;

public interface UserDao extends JpaRepository<User, Integer>{

    /*
     * findByUsername
     * createUser
     */

    @Transactional
    @Modifying
    @Query(value = "insert into users values (default, :password , :username)", nativeQuery = true)
    void createUser(@Param("password") String password,@Param("username") String username);

    Optional<User> findByUsername(String username);
    
}
