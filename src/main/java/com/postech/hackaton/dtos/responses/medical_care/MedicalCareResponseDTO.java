package com.postech.hackaton.dtos.responses.medical_care;

import com.postech.hackaton.domain.enums.MedicalCarePriority;
import com.postech.hackaton.domain.enums.MedicalCareStatus;

public record MedicalCareResponseDTO(
        Long id,
        String document,
        Short age,
        MedicalCarePriority priority,
        String description,
        String aiJustification,
        Boolean priorityAccess,
        String phoneNumber,
        MedicalCareStatus status
) {
}
