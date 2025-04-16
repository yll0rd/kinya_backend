package com.kinya.kinya_backend.user.controller;

import com.kinya.kinya_backend.user.User;
import com.kinya.kinya_backend.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    public Controller(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    @GetMapping
    public List<User> getStudents() {
        return this.userRepository.findAll();
    }


}