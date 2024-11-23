package com.example.user_service.controllers;

import com.example.user_service.dtos.exceptions.NotValidException;
import com.example.user_service.dtos.requests.CreateUserRequestDTO;
import com.example.user_service.dtos.s2s.requests.CreateUserInPostsRequestDTO;
import com.example.user_service.dtos.s2s.response.CreateUserInPostsResponseDTO;
import com.example.user_service.entities.User;
import com.example.user_service.repositories.UserRepository;
import com.example.user_service.services.s2s.PostsServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PostsServiceManager postsServiceManager;

    @PostMapping("/users")
    @Transactional
    public User createUser(@RequestBody CreateUserRequestDTO req) {
        User user = new User();
        user.setEmail(req.getEmail());
        user.setUsername(req.getUsername());
        if (!req.getPassword().equals(req.getConfirmPassword())) {
            throw new NotValidException("Password and Confirm Password do not match.");
        }
        user.setPasswordHash(passwordEncoder.encode(req.getPassword()));
        user.setRole(req.getRole());
        userRepo.save(user);

//        CreateUserInPostsRequestDTO createUserInPostsRequestDTO = new CreateUserInPostsRequestDTO(user.getUsername(), user.getEmail(), req.getUsername(), null);
//        boolean isUserCreatedInPosts = postsServiceManager.createUserInPosts(createUserInPostsRequestDTO);
//        if (!isUserCreatedInPosts) {
//            throw new RuntimeException("User not created in Posts.");
//        }

        return user;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
}
