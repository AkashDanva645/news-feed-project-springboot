package com.example.posts_service.dtos.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomErrorResponse {
    private int statusCode;
    private String message;
    private String detail;
    private long timestamp;
}
