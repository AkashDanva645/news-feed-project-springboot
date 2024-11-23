package com.example.posts_service.dtos.requests.admin_requests;

import com.example.posts_service.entities.enums.UserFollowingStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminCreateUserFollowingRequest {
    private String followerId;
    private String followingId;
    private UserFollowingStatus status;

    public AdminCreateUserFollowingRequest(Map<String, Object> reqBody) {
        this.followerId = (String) reqBody.get("followerId");
        this.followingId = (String) reqBody.get("followingId");
        this.status = UserFollowingStatus.getByEnumName((String) reqBody.get("status"));
    }
}
