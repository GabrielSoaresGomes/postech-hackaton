package com.postech.hackaton.dtos.requests.medical_care;

public record CreateMedicalCareRequestDTO(
        String document,
        Short age,
        String description,
        Boolean priorityAccess,
        String phoneNumber
) {
}
