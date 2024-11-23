package com.example.posts_service.controllers;

import com.example.posts_service.dtos.requests.admin_requests.AdminCreateUserRequest;
import com.example.posts_service.entities.User;
import com.example.posts_service.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    public UserRepository userRepo;

    @PostMapping("/s2s/createUser")
    public User createUser(@RequestBody AdminCreateUserRequest requestDTO) {
        User user = User.builder().email(requestDTO.getEmail()).firstname(requestDTO.getFirstname()).lastname(requestDTO.getLastname()).username(requestDTO.getUsername()).build();
        userRepo.save(user);

        return user;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getName() + " " + auth.getAuthorities() + " " + auth.getCredentials());

        return userRepo.findAll();
    }
}
