package com.example.posts_service.dtos.responses;

import com.example.posts_service.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserResponse {
    private String userId;
    private String username;
    private String email;
    private String firstname;
    private String lastname;
    private Date createdAt;

    public CreateUserResponse(User user) {
        this.setUserId(user.getId());
        this.setEmail(user.getEmail());
        this.setUsername(user.getUsername());
        this.setFirstname(user.getEmail());
        this.setLastname(user.getLastname());
        this.setCreatedAt(user.getCreatedAt());
    }
}
