package com.postech.hackaton.infrastructure.controllers;

import com.postech.hackaton.dtos.responses.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/users")
@Tag(name = "Users", description = "Endpoints for user management")
@SecurityRequirement(name = "bearerAuth")
public class UserController {

    @GetMapping
    @Operation(summary = "List all users")
    public ResponseEntity<List<UserResponse>> listAll() {
        return ResponseEntity.ok(new ArrayList<>());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID")
    public ResponseEntity<UserResponse> getById(@PathVariable Long id) {
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }
}