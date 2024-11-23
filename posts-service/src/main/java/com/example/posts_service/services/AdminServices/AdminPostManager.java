package com.example.posts_service.services.AdminServices;

import com.example.posts_service.dtos.exceptions.NotFoundException;
import com.example.posts_service.dtos.exceptions.NotValidException;
import com.example.posts_service.dtos.requests.admin_requests.AdminCreatePostRequest;
import com.example.posts_service.entities.AppEntity;
import com.example.posts_service.entities.Like;
import com.example.posts_service.entities.Post;
import com.example.posts_service.entities.User;
import com.example.posts_service.repositories.PostRepository;
import com.example.posts_service.repositories.UserRepository;
import com.example.posts_service.services.AdminEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AdminPostManager implements AdminEntityManager {

    @Autowired
    private PostRepository postRepo;

    @Autowired
    private UserRepository userRepo;


    @Override
    public AppEntity create(Map<String, Object> reqBody) {
        AdminCreatePostRequest createPostRequest = new AdminCreatePostRequest(reqBody);

        Post post = new Post();
        post.setContent(createPostRequest.getContent());

        Optional<User> user = userRepo.findById(createPostRequest.getUserId());
        if (user.isEmpty()) {
            throw new NotFoundException("User not found with Id: " + createPostRequest.getUserId());
        }

        post.setUser(user.get());

        return postRepo.save(post);
    }


    @Override
    public AppEntity update(Map<String, Object> reqBody) {
        return null;
    }


    @Override
    public AppEntity delete(Map<String, Object> reqBody) {
        return null;
    }


    @Override
    public List<AppEntity> getAll() {
        return null;
    }


    @Override
    public AppEntity getOne(String id) {
        return null;
    }
}
