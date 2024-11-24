package com.example.posts_service.dtos.requests;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
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
public class CreateUserRequest {
    @NotEmpty
    @NotEmpty(message = "username can't be blank")
    private String username;

    @NotEmpty
    @Email(message = "Invalid email format.")
    private String email;

    @NotEmpty(message = "firstname can't be blank")
    private String firstname;

    private String lastname;
}