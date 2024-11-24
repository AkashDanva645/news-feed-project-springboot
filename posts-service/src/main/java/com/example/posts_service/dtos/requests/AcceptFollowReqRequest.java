package com.example.posts_service.dtos.requests;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AcceptFollowReqRequest {
    @NotEmpty(message = "Please provide valid followingId.")
    private String followingId;
}
