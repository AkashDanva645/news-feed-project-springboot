package com.example.user_service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AppPropertiesConfig {

    @Value("${jwt.secret.key}")
    private String SECRET_KEY;

    @Value("${jwt.expires.in.minutes}")
    private Integer JWT_EXPIRES_IN_MINUTES;

    public String getSecretKey() {
        if (SECRET_KEY == null) {
            throw new RuntimeException("Unable to read SECRET_KEY from application.properties");
        }
        return SECRET_KEY;
    }

    public Integer getJwtExpiresInMinutes() {
        return JWT_EXPIRES_IN_MINUTES;
    }
}
