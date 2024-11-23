package com.example.posts_service.security;

import com.example.posts_service.dtos.exceptions.NotValidException;
import com.example.posts_service.dtos.exceptions.UnauthorizedException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException, UnauthorizedException {

        String token = request.getHeader("Authorization");
        try {
            if (token != null && token.startsWith("Bearer ")) {
                token = token.substring(7);
                String validateTokenResponse = jwtUtils.validateToken(token);
                if (!validateTokenResponse.equals("Valid")) {
                    throw new UnauthorizedException(validateTokenResponse);
                }
                String username = jwtUtils.extractUsername(token);
                List<GrantedAuthority> authorityList = jwtUtils.extractAuthorities(token);

                JwtAuthentication authentication = new JwtAuthentication(username, authorityList, true);

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            filterChain.doFilter(request, response);

        } catch (UnauthorizedException ex) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("error", "Unauthorized");
            responseBody.put("message", ex.getDetailedMessage());

            response.getWriter().write(objectMapper.writeValueAsString(responseBody));
        }
    }
}
