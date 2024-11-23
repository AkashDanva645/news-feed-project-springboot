package com.example.user_service.dtos.others;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class GeneratedTokenDTO {
    private String token;
    private Date expirationTime;
}
