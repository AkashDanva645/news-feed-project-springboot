package com.example.user_service.dtos.s2s.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserInPostsRequestDTO {
    private String username;
    private String email;
    private String firstname;
    private String lastname;
}
