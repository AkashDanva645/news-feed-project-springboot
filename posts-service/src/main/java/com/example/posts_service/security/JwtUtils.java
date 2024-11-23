package com.example.posts_service.security;


import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;


import java.util.*;

@Component
public class JwtUtils {

    @Value("${jwt.secret.key}")
    private String JWT_SECRET_KEY;

    public String validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(JWT_SECRET_KEY) // Validate signature
                    .parseClaimsJws(token);
            return "Valid";
        } catch (ExpiredJwtException e) {
            return "Token expired";
        } catch (UnsupportedJwtException e) {
            return "Unsupported JWT";
        } catch (MalformedJwtException e) {
            return "Malformed JWT";
        } catch (SignatureException e) {
            return "Invalid signature";
        } catch (IllegalArgumentException e) {
            return "JWT is empty";
        }
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getBody().getSubject();
    }

    public Boolean isTokenExpired(String token) {
        return extractAllClaims(token).getBody().getExpiration().before(new Date());
    }

    public Jws<Claims> extractAllClaims(String token) {
        Jws<Claims> jwsClaims = Jwts.parser()
                .setSigningKey(JWT_SECRET_KEY)
                .parseClaimsJws(token);
        return jwsClaims;
    }

    public List<GrantedAuthority> extractAuthorities(String token) {
        String role = (String) extractAllClaims(token).getBody().get("role");
        return List.of(new SimpleGrantedAuthority(role));
    }
}
