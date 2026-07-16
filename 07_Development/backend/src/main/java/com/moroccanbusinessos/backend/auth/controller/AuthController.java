package com.moroccanbusinessos.backend.auth.controller;

import com.moroccanbusinessos.backend.auth.dto.LoginRequest;
import com.moroccanbusinessos.backend.auth.dto.LoginResponse;
import com.moroccanbusinessos.backend.auth.service.AuthService;
import com.moroccanbusinessos.backend.common.response.ApiResponse;
import com.moroccanbusinessos.backend.user.dto.RegisterRequest;
import com.moroccanbusinessos.backend.user.entity.User;
import com.moroccanbusinessos.backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<User>> register(
            @RequestBody RegisterRequest request) {

        User user = userService.register(request);

        ApiResponse<User> response = ApiResponse.<User>builder()
                .success(true)
                .message("User registered successfully.")
                .data(user)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest request) {

        return ResponseEntity.ok(authService.login(request));
    }
}