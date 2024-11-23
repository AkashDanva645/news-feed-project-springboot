package com.example.posts_service.services.AdminServices;

import com.example.posts_service.dtos.exceptions.NotFoundException;
import com.example.posts_service.dtos.exceptions.NotValidException;
import com.example.posts_service.dtos.requests.admin_requests.AdminCreateLikeRequest;
import com.example.posts_service.entities.*;
import com.example.posts_service.repositories.LikeRepository;
import com.example.posts_service.repositories.PostRepository;
import com.example.posts_service.repositories.UserRepository;
import com.example.posts_service.services.AdminEntityManager;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AdminLikeManager implements AdminEntityManager {

    @Autowired
    private LikeRepository likeRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PostRepository postRepo;

    @Override
    public AppEntity create(Map<String, Object> reqBody) {
        AdminCreateLikeRequest createLikeRequest = new AdminCreateLikeRequest(reqBody);
        Like like = new Like();

        Optional<User> user = userRepo.findById(createLikeRequest.getUserId());
        if (user.isEmpty()) {
            throw new NotFoundException("User not found with Id: " + createLikeRequest.getUserId());
        }
        like.setUser(user.get());

        Optional<Post> post = postRepo.findById(createLikeRequest.getPostId());
        if (post.isEmpty()) {
            throw new NotFoundException("Post not found with Id: " + createLikeRequest.getPostId());
        }
        like.setPost(post.get());

        return likeRepo.save(like);
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
