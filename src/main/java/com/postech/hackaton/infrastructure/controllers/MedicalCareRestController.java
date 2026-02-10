package com.postech.hackaton.infrastructure.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.postech.hackaton.application.gateways.AiTriageGateway;
import com.postech.hackaton.dtos.requests.medical_care.CreateMedicalCareRequestDTO;
import com.postech.hackaton.dtos.requests.medical_care.ListMedicalCareRequestDTO;
import com.postech.hackaton.dtos.responses.medical_care.MedicalCareResponseDTO;
import com.postech.hackaton.interface_adapter.controllers.MedicalCareController;
import com.postech.hackaton.interface_adapter.data_sources.repositories.MedicalCareRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/medical-care")
@Tag(name = "Medical Care", description = "Endpoints for medical care")
public class MedicalCareRestController {
    private final MedicalCareController medicalCareController;

    public MedicalCareRestController(
            MedicalCareRepository medicalCareRepository,
            AiTriageGateway aiTriageGateway,
            ObjectMapper objectMapper
    ) {
        this.medicalCareController = new MedicalCareController(medicalCareRepository, aiTriageGateway, objectMapper);
    }

    @GetMapping
    @Operation(summary = "List medical cares by page")
    public List<MedicalCareResponseDTO> list(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        var request = new ListMedicalCareRequestDTO(page, size);

        return this.medicalCareController.list(request);
    }

    @GetMapping("{id}")
    @Operation(summary = "Find one medical care")
    public MedicalCareResponseDTO find(@PathVariable("id") long id) {
        return this.medicalCareController.find(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Operation(summary = "Create medical cares")
    public MedicalCareResponseDTO create(@RequestBody CreateMedicalCareRequestDTO request) {
        return this.medicalCareController.create(request);
    }
}
