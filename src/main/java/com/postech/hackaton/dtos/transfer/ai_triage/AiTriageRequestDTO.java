package com.postech.hackaton.dtos.transfer.ai_triage;

import java.util.Map;

public record AiTriageRequestDTO(
        Map<String, Object> triageData,
        String prompt
) {}