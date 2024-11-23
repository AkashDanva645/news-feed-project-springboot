package com.example.posts_service.controllers;

import com.example.posts_service.dtos.exceptions.NotFoundException;
import com.example.posts_service.dtos.requests.admin_requests.AdminCreatePostRequest;
import com.example.posts_service.entities.Post;
import com.example.posts_service.entities.User;
import com.example.posts_service.repositories.PostRepository;
import com.example.posts_service.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class PostController {

    @Autowired
    private PostRepository postRepo;

    @Autowired
    private UserRepository userRepo;

    @PostMapping("/posts")
    public Post createPost(@RequestBody AdminCreatePostRequest reqBody) {
        Post post = new Post();
        post.setContent(reqBody.getContent());

        Optional<User> user = userRepo.findById(reqBody.getUserId());
        if (user.isEmpty()) {
            throw new NotFoundException("User not found.");
        }

        post.setUser(user.get());
        postRepo.save(post);
        return post;
    }
}
