package com.example.user_service.dtos.requests;

import com.example.user_service.entities.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateUserRequestDTO {
    private String username;
    private String email;
    private String password;
    private String confirmPassword;
    private UserRole role;
}
