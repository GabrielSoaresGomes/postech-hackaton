package com.postech.hackaton.infrastructure.controllers;

import com.postech.hackaton.application.usecases.ClassifyTriageWithAiUseCase;
import com.postech.hackaton.dtos.transfer.AiTriageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ai/triage")
public class AiTriagePocController {

    private final ClassifyTriageWithAiUseCase useCase;

    @PostMapping("/classify")
    public ResponseEntity<AiTriageResponse> classify(@RequestBody Map<String, Object> triageData) {
        return ResponseEntity.ok(useCase.execute(triageData));
    }
}
