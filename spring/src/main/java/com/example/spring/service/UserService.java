package com.example.spring.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring.entities.Authentication;
import com.example.spring.entities.User;
import com.example.spring.exception.AccountFailure;
import com.example.spring.repository.UserDao;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public String register(User user){
        try {
            this.userDao.createUser(user.getUsername(), user.getPassword());
            return "Account created";            
        } catch (Exception e) { // need to make more specific
            throw new AccountFailure("there was a problem creating your account");
        }

    }

    public User authenticate(Authentication credentials){
        Optional<User> u = this.userDao.findByUsername(credentials.getUsername());
        if(u.isPresent()){
            if(u.get().getPassword().equals(credentials.getPassword())){
                return u.get();
            }
        }
        throw new AccountFailure("login failed: please try again");
    }
    
}
