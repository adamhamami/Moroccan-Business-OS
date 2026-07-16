package com.moroccanbusinessos.backend.user.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
public class UserController {

    @GetMapping("/api/v1/users/me")
    public String me(Authentication authentication) {
        return "Connected as : " + authentication.getName();
    }
}