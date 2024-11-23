package com.example.posts_service.services.AdminServices;

import com.example.posts_service.dtos.exceptions.NotFoundException;
import com.example.posts_service.dtos.exceptions.NotValidException;
import com.example.posts_service.dtos.requests.admin_requests.AdminCreateUserFollowingRequest;
import com.example.posts_service.entities.AppEntity;
import com.example.posts_service.entities.Post;
import com.example.posts_service.entities.User;
import com.example.posts_service.entities.UserFollowing;
import com.example.posts_service.repositories.UserFollowingRepository;
import com.example.posts_service.repositories.UserRepository;
import com.example.posts_service.services.AdminEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AdminUserFollowingManager implements AdminEntityManager {

    @Autowired
    private UserFollowingRepository userFollowingRepo;

    @Autowired
    private UserRepository userRepo;


    @Override
    public AppEntity create(Map<String, Object> reqBody) {
        AdminCreateUserFollowingRequest userFollowingRequest = new AdminCreateUserFollowingRequest(reqBody);
        UserFollowing userFollowing = new UserFollowing();
        userFollowing.setFollowedAt(new Date());

        Optional<User> follower = userRepo.findById(userFollowingRequest.getFollowerId());
        if (follower.isEmpty()) {
            throw new NotFoundException("User not found with Id: " + userFollowingRequest.getFollowerId());
        }
        userFollowing.setFollower(follower.get());

        Optional<User> following = userRepo.findById(userFollowingRequest.getFollowingId());
        if (following.isEmpty()) {
            throw new NotFoundException("User not found with Id: " + userFollowingRequest.getFollowingId());
        }
        userFollowing.setFollowing(following.get());

        return userFollowingRepo.save(userFollowing);
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
