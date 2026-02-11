package com.postech.hackaton.infrastructure.controllers;

import com.postech.hackaton.dtos.requests.LoginRequest;
import com.postech.hackaton.dtos.requests.user.CreateUserRequestDTO;
import com.postech.hackaton.dtos.responses.UserResponse;
import com.postech.hackaton.dtos.security.JwtToken;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Endpoints for user authentication")
public class AuthController {

    @PostMapping("/login")
    @Operation(summary = "User login", description = "Authenticate user with login and password")
    public ResponseEntity<JwtToken> login(@RequestBody LoginRequest request) {
        JwtToken token = JwtToken.builder()
                .token("mock-token")
                .type("Bearer")
                .expiresIn(3600000L)
                .build();
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    @Operation(summary = "Register new user", description = "Create a new user account")
    public ResponseEntity<UserResponse> register(@RequestBody CreateUserRequestDTO request) {
        UserResponse response = UserResponse.builder()
                .id(1L)
                .userType(request.userType())
                .name(request.name())
                .email(request.email())
                .login(request.login())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}