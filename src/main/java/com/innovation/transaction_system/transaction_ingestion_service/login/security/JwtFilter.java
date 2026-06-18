package com.innovation.transaction_system.transaction_ingestion_service.login.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public void doFilterInternal(

            HttpServletRequest request,

            HttpServletResponse response,

            FilterChain filterChain)

            throws ServletException, IOException {

        String authHeader =
                request.getHeader("Authorization");

        if(authHeader != null
                &&
                authHeader.startsWith("Bearer ")) {

            String token =
                    authHeader.substring(7);

            boolean valid =
                    jwtUtil.validateToken(token);

            if(!valid) {

                response.setStatus(401);

                response.getWriter()
                        .write("Invalid Token");

                return;
            }
        }

        filterChain.doFilter(
                request,
                response
        );
    }
}
