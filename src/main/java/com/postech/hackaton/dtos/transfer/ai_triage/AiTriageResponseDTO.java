package com.postech.hackaton.dtos.transfer.ai_triage;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AiTriageResponseDTO(
        String priority,
        String rationale,
        Double confidence
) {}
