package com.example.posts_service.services.AdminServices;

import com.example.posts_service.dtos.exceptions.NotFoundException;
import com.example.posts_service.dtos.exceptions.NotValidException;
import com.example.posts_service.dtos.requests.admin_requests.AdminCreateUserRequest;
import com.example.posts_service.entities.AppEntity;
import com.example.posts_service.entities.User;
import com.example.posts_service.repositories.UserRepository;
import com.example.posts_service.services.AdminEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AdminUserManager implements AdminEntityManager {

    @Autowired
    private UserRepository userRepo;


    @Override
    public AppEntity create(Map<String, Object> reqBody) {
        AdminCreateUserRequest createUserRequest = new AdminCreateUserRequest(reqBody);
        User user = new User();
        user.setUsername(createUserRequest.getUsername());
        user.setEmail(createUserRequest.getEmail());
        user.setFirstname(createUserRequest.getFirstname());
        user.setLastname(createUserRequest.getLastname());

        return userRepo.save(user);
    }


    @Override
    public AppEntity update(Map<String, Object> reqBody) {
        return new User();
    }


    @Override
    public AppEntity delete(Map<String, Object> reqBody) {
        return new User();
    }


    @Override
    public List<AppEntity> getAll() {
        List<AppEntity> res = new ArrayList<>(userRepo.findAll());
        return res;
    }


    @Override
    public AppEntity getOne(String id) {
        Optional<User> x = userRepo.findById(id);
        if (x.isEmpty()) {
            throw new NotFoundException("User not found with Id: " + id);
        }
        return x.get();
    }
}
