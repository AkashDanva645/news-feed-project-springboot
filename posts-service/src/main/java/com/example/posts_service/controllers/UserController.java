package com.example.posts_service.controllers;

import com.example.posts_service.dtos.requests.AcceptFollowReqRequest;
import com.example.posts_service.dtos.requests.CreateUserRequest;
import com.example.posts_service.dtos.requests.SendFollowReqRequest;
import com.example.posts_service.dtos.requests.admin_requests.AdminCreateUserRequest;
import com.example.posts_service.dtos.responses.CreateUserResponse;
import com.example.posts_service.entities.User;
import com.example.posts_service.repositories.UserRepository;
import com.example.posts_service.services.Services.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    public UserManager userManager;

    @PostMapping("/s2s/createUser")
    public CreateUserResponse createUserHandler(@RequestBody CreateUserRequest req) {
        System.out.println(req);
        return new CreateUserResponse(userManager.createUser(req));
    }

    @PostMapping("/sendFollowRequest")
    public String sendFollowRequestHandler(@RequestBody SendFollowReqRequest req) {
        String followerUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        return userManager.sendFollowRequest(followerUsername, req.getFollowingId());
    }

    @PostMapping("/acceptFollowRequest")
    public String acceptFollowRequestHandler(@RequestBody AcceptFollowReqRequest req) {
        String followerUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        return userManager.acceptFollowRequest(followerUsername, req.getFollowingId());
    }
}
