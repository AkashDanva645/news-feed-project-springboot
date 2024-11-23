package com.example.posts_service.dtos.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NotValidException extends RuntimeException {
    private String detailedMessage;
}
