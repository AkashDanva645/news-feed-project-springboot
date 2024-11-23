package com.example.user_service.dtos.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NotValidException extends RuntimeException {
    private String detailMessage;
}
