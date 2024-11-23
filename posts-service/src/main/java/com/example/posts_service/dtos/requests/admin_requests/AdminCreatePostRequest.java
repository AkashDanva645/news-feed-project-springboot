package com.example.posts_service.dtos.requests.admin_requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminCreatePostRequest {
    private String content;
    private String userId;

    public AdminCreatePostRequest(Map<String, Object> reqBody) {
        this.content = (String) reqBody.get("content");
        this.userId = (String) reqBody.get("userId");
    }
}
