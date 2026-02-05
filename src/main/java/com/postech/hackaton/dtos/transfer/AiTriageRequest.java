package com.postech.hackaton.dtos.transfer;

import java.util.Map;

public record AiTriageRequest(
        Map<String, Object> triageData,
        String prompt
) {}