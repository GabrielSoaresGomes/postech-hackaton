package com.postech.hackaton.application.services.ai_triage;

import com.postech.hackaton.domain.MedicalCare;
import com.postech.hackaton.dtos.requests.medical_care.CreateMedicalCareRequestDTO;

import java.util.LinkedHashMap;
import java.util.Map;

public class AiTriageDataExtractor {

    public Map<String, Object> extract(CreateMedicalCareRequestDTO request, MedicalCare medicalCare) {
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("age", request.age());
        data.put("priorityAccess", request.priorityAccess());
        data.put("description", request.description());
        return data;
    }
}
