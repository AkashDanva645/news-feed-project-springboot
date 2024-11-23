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
public class AdminCreateLikeRequest {
    private String userId;
    private String postId;

    public AdminCreateLikeRequest(Map<String, Object> reqBody) {
        this.userId = (String) reqBody.get("userId");
        this.postId = (String) reqBody.get("postId");
    }
}
