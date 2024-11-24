package com.example.user_service.controllers;

import com.example.user_service.dtos.s2s.requests.CreateUserInPostsRequestDTO;
import com.example.user_service.entities.enums.UserRole;
import com.example.user_service.dtos.exceptions.NotFoundException;
import com.example.user_service.dtos.exceptions.NotValidException;
import com.example.user_service.dtos.others.GeneratedTokenDTO;
import com.example.user_service.dtos.others.UserJwtPayload;
import com.example.user_service.dtos.requests.LoginRequestDTO;
import com.example.user_service.dtos.requests.SignUpRequestDTO;
import com.example.user_service.entities.User;
import com.example.user_service.repositories.UserRepository;
import com.example.user_service.security.JwtUtils;
import com.example.user_service.services.s2s.PostsServiceManager;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AuthenticationController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PostsServiceManager postsServiceManager;

    @Autowired
    private JwtUtils jwtUtils;


    @PostMapping("/signup")
    @Transactional
    public GeneratedTokenDTO signUp(@RequestBody SignUpRequestDTO reqBody) {
        User user = new User();
        user.setEmail(reqBody.getEmail());
        user.setUsername(reqBody.getUsername());
        if (!reqBody.getPassword().equals(reqBody.getConfirmPassword())) {
            throw new NotValidException("Password and Confirm Password do not match.");
        }

        user.setPasswordHash(passwordEncoder.encode(reqBody.getPassword()));
        user.setRole(UserRole.USER);
        UserJwtPayload payload = new UserJwtPayload(user.getUsername(), user.getEmail(), user.getRole().getValue());
        GeneratedTokenDTO tokenDTO = jwtUtils.generateTokenAfterAuthentication(payload);
        userRepo.save(user);

        CreateUserInPostsRequestDTO createUserInPostsRequestDTO = new CreateUserInPostsRequestDTO(user.getUsername(), user.getEmail(), reqBody.getFirstname(), reqBody.getLastname());
        boolean isUserCreatedInPosts = postsServiceManager.createUserInPosts(createUserInPostsRequestDTO);
        if (!isUserCreatedInPosts) {
            throw new RuntimeException("User not created in Posts.");
        }
        return tokenDTO;
    }


    @PostMapping("/login")
    public GeneratedTokenDTO login(@RequestBody LoginRequestDTO reqBody) {
        Optional<User> optUser = userRepo.findByUsernameOrEmail(reqBody.getUsernameOrEmail());
        if (optUser.isEmpty()) {
            throw new NotFoundException("User not found with given Username/Email");
        }
        User user = optUser.get();

        if (!passwordEncoder.matches(reqBody.getPassword(), user.getPasswordHash())) {
            throw new NotValidException("Wrong Password.");
        }

        UserJwtPayload payload = new UserJwtPayload(user.getUsername(), user.getEmail(), user.getRole().getValue());
        return jwtUtils.generateTokenAfterAuthentication(payload);
    }


    @GetMapping("/extractTokenClaims")
    public Jws<Claims> extractJwtClaims(@RequestParam String token) {
        if (token.isEmpty()) throw new NotValidException("Token not found in request.");
        return jwtUtils.extractAllClaims(token);
    }
}
