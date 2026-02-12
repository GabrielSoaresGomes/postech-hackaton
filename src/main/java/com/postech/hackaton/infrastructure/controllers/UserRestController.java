package com.postech.hackaton.infrastructure.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.postech.hackaton.application.gateways.AiTriageGateway;
import com.postech.hackaton.dtos.requests.medical_care.CreateMedicalCareRequestDTO;
import com.postech.hackaton.dtos.requests.user.CreateUserRequestDTO;
import com.postech.hackaton.dtos.requests.user.ListUserRequestDTO;
import com.postech.hackaton.dtos.responses.UserResponse;
import com.postech.hackaton.dtos.responses.medical_care.MedicalCareResponseDTO;
import com.postech.hackaton.interface_adapter.controllers.MedicalCareController;
import com.postech.hackaton.interface_adapter.controllers.UserController;
import com.postech.hackaton.interface_adapter.data_sources.repositories.MedicalCareRepository;
import com.postech.hackaton.interface_adapter.data_sources.repositories.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/users")
@Tag(name = "Users", description = "Endpoints for user management")
@SecurityRequirement(name = "bearerAuth")
public class UserRestController {
    private final UserController userController;

    public UserRestController(
            UserRepository userRepository
    ) {
        this.userController = new UserController(userRepository);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Operation(summary = "Create users")
    public UserResponse create(@RequestBody CreateUserRequestDTO request) {
        return this.userController.create(request);
    }

    @GetMapping
    @Operation(summary = "List all users")
    public List<UserResponse> list(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        var request = new ListUserRequestDTO(page, size);
        return this.userController.list(request);
    }

    @GetMapping("{id}")
    @Operation(summary = "Get user by ID")
    public UserResponse getById(@PathVariable("id") Long id) {
        return this.userController.find(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user")
    public Integer delete(@PathVariable("id") Long id) {
        return this.userController.delete(id);
    }
}