package com.example.posts_service.controllers;

import com.example.posts_service.entities.AppEntity;
import com.example.posts_service.services.AdminEntityManager;
import com.example.posts_service.services.AdminServices.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    public AdminUserManager adminUserManager;

    @Autowired
    public AdminCommentManager adminCommentManager;

    @Autowired
    public AdminPostManager adminPostManager;

    @Autowired
    public AdminLikeManager adminLikeManager;

    @Autowired
    public AdminUserFollowingManager adminUserFollowingManager;

    public AdminEntityManager adminEntityManager;

    private void switchService(String entityName) {
        switch (entityName) {
            case "users":
                this.adminEntityManager = adminUserManager;
                break;
            case "comments":
                this.adminEntityManager = adminCommentManager;
                break;
            case "posts":
                this.adminEntityManager = adminPostManager;
                break;
            case "likes":
                this.adminEntityManager = adminLikeManager;
                break;
            case "user-following":
                this.adminEntityManager = adminUserFollowingManager;
                break;
        }
    }

    @GetMapping("/{entity_name}/getAll")
    public List<AppEntity> getAll(@PathVariable("entity_name") String entityName) {
        switchService(entityName);
        return this.adminEntityManager.getAll();
    }

    @PostMapping("/{entity_name}/create")
    public AppEntity createEntity(@RequestBody Map<String, Object> reqBody, @PathVariable("entity_name") String entityName) {
        switchService(entityName);
        return this.adminEntityManager.create(reqBody);
    }
}
