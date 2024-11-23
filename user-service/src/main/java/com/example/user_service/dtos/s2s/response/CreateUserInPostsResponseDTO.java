package com.example.user_service.dtos.s2s.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateUserInPostsResponseDTO {
    private String id;
    private String username;
    private String email;
    private String firstname;
    private String lastname;
}
