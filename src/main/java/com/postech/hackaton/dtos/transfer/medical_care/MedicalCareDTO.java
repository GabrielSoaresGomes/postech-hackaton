package com.postech.hackaton.dtos.transfer.medical_care;

import com.postech.hackaton.domain.enums.MedicalCarePriority;
import com.postech.hackaton.domain.enums.MedicalCareStatus;

import java.time.LocalDateTime;

public record MedicalCareDTO(
        Long id,
        String document,
        Short age,
        MedicalCarePriority priority,
        String description,
        String aiJustification,
        Boolean priorityAccess,
        String phoneNumber,
        MedicalCareStatus status,
        LocalDateTime createdAt,
        LocalDateTime lastModifiedAt,
        LocalDateTime deletedAt
) {
}
