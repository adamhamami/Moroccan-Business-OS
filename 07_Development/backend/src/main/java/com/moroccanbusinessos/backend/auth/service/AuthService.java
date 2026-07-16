package com.moroccanbusinessos.backend.auth.service;

import com.moroccanbusinessos.backend.auth.dto.LoginRequest;
import com.moroccanbusinessos.backend.auth.dto.LoginResponse;
import com.moroccanbusinessos.backend.auth.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public LoginResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        String token = jwtService.generateToken(request.getEmail());

        return LoginResponse.builder()
                .token(token)
                .type("Bearer")
                .build();
    }
}