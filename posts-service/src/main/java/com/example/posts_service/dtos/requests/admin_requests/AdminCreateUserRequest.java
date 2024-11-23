package com.example.posts_service.dtos.requests.admin_requests;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminCreateUserRequest {
    private String username;
    private String email;
    private String firstname;
    private String lastname;

    public AdminCreateUserRequest(Map<String, Object> reqBody) {
        this.username = (String) reqBody.get("username");
        this.email = (String) reqBody.get("email");
        this.firstname = (String) reqBody.get("firstname");
        this.lastname = (String) reqBody.get("lastname");
    }
}
