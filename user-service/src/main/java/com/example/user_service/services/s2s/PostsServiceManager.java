package com.example.user_service.services.s2s;

import com.example.user_service.dtos.s2s.requests.CreateUserInPostsRequestDTO;
import com.example.user_service.dtos.s2s.response.CreateUserInPostsResponseDTO;
import com.example.user_service.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PostsServiceManager {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${post.service.url}")
    private String POST_SERVICE_URL;

    private static final String CREATE_USER_IN_POSTS_API_URL = "/api/v1/s2s/createUser";

    public boolean createUserInPosts(CreateUserInPostsRequestDTO requestDTO) {
        ResponseEntity<CreateUserInPostsResponseDTO> response = restTemplate.postForEntity(POST_SERVICE_URL + CREATE_USER_IN_POSTS_API_URL, requestDTO, CreateUserInPostsResponseDTO.class);
        try {
            System.out.println(response.getBody().toString());
        } catch (Exception e) {}
        System.out.println(response.getStatusCode());
        if (response.getStatusCode().is2xxSuccessful()) return true;
        return false;
    }
}
