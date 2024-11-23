package com.example.posts_service.services;

import com.example.posts_service.entities.AppEntity;
import com.example.posts_service.entities.User;
import jakarta.persistence.Entity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface AdminEntityManager {
    AppEntity create(Map<String, Object> reqBody);
    AppEntity update(Map<String, Object> reqBody);
    AppEntity delete(Map<String, Object> reqBody);
    List<AppEntity> getAll();
    AppEntity getOne(String id);
}
