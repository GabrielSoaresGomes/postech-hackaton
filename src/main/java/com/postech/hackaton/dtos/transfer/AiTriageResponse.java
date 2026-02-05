package com.postech.hackaton.dtos.transfer;

public record AiTriageResponse(
        String priority,
        String rationale,
        String destination,
        Double confidence
) {}
