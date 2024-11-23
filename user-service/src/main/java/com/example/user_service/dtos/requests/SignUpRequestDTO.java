package com.example.user_service.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SignUpRequestDTO {
    private String username;
    private String email;
    private String password;
    private String confirmPassword;
}
