package com.postech.hackaton.infrastructure.controllers;

import com.postech.hackaton.dtos.requests.CreateScreeningRequest;
import com.postech.hackaton.dtos.responses.ScreeningResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/screenings")
@Tag(name = "Screenings", description = "Endpoints for screening management")
@SecurityRequirement(name = "bearerAuth")
public class ScreeningController {

    @GetMapping
    @Operation(summary = "List all screenings")
    public ResponseEntity<List<ScreeningResponse>> listAll() {
        return ResponseEntity.ok(new ArrayList<>());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get screening by ID")
    public ResponseEntity<ScreeningResponse> getById(@PathVariable Long id) {
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Operation(summary = "Create new screening")
    public ResponseEntity<ScreeningResponse> create(@RequestBody CreateScreeningRequest request) {
        ScreeningResponse response = ScreeningResponse.builder()
                .id(1L)
                .patientId(request.getPatientId())
                .description(request.getDescription())
                .priority(request.getPriority())
                .completed(false)
                .createdAt(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update screening")
    public ResponseEntity<ScreeningResponse> update(@PathVariable Long id, @RequestBody CreateScreeningRequest request) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete screening")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }
}