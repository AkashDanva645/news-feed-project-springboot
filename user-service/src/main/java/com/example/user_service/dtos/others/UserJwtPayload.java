package com.example.user_service.dtos.others;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserJwtPayload {
    private String username;
    private String email;
    private String role;
}
