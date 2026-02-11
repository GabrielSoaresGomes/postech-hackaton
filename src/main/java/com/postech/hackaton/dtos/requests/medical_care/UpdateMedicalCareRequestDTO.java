package com.postech.hackaton.dtos.requests.medical_care;

public record UpdateMedicalCareRequestDTO(
        Long id,
        UpdateMedicalCareDataRequestDTO data
) {
}
