package com.example.user_service.security;

import com.example.user_service.config.AppPropertiesConfig;
import com.example.user_service.dtos.others.GeneratedTokenDTO;
import com.example.user_service.dtos.others.UserJwtPayload;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {

    @Autowired
    private AppPropertiesConfig appConfig;

    public GeneratedTokenDTO generateTokenAfterAuthentication(UserJwtPayload jwtPayload) {
        Date expirationTime = new Date(System.currentTimeMillis() + 1000L * 60 * appConfig.getJwtExpiresInMinutes());
        String token = generateToken(jwtPayload, expirationTime);
        return new GeneratedTokenDTO(token, expirationTime);
    }

    private String generateToken(UserJwtPayload jwtPayload, Date expirationTime) {
        Map<String, Object> headers = new HashMap<>();
        headers.put("alg", SignatureAlgorithm.HS256.getValue());
        headers.put("typ", "jwt");

        Map<String, Object> publicClaims = new HashMap<>();
        publicClaims.put("email", jwtPayload.getEmail());
        publicClaims.put("role", jwtPayload.getRole());
        publicClaims.put("sub", jwtPayload.getUsername());

        return Jwts.builder()
                .setIssuedAt(new Date())
                .setIssuer("Auth-Service")
                .setHeader(headers)
                .setClaims(publicClaims)
                .setExpiration(expirationTime)
                .signWith(SignatureAlgorithm.HS256, appConfig.getSecretKey())
                .compact();
    }

    public Boolean validateToken(String token, String username) {
        String tokenUsername = extractUsername(token);
        return username.equals(tokenUsername) && !isTokenExpired(token);
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getBody().getSubject();
    }

    public Boolean isTokenExpired(String token) {
        return extractAllClaims(token).getBody().getExpiration().before(new Date());
    }

    public Jws<Claims> extractAllClaims(String token) {
        Jws<Claims> jwsClaims = Jwts.parser()
                .setSigningKey(appConfig.getSecretKey())
                .parseClaimsJws(token);
        return jwsClaims;
    }
}
