package com.moroccanbusinessos.backend.health.controller;

import com.moroccanbusinessos.backend.common.response.ApiResponse;
import com.moroccanbusinessos.backend.health.dto.HealthResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/health")
public class HealthController {

    @GetMapping
    public ApiResponse<HealthResponse> health() {

        HealthResponse response = HealthResponse.builder()
                .application("Moroccan Business OS")
                .version("1.0.0")
                .status("UP")
                .build();

        return ApiResponse.<HealthResponse>builder()
                .success(true)
                .message("Application is running successfully.")
                .data(response)
                .build();
    }
}