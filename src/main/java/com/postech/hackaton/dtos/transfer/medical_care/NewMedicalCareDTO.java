package com.postech.hackaton.dtos.transfer.medical_care;

import com.postech.hackaton.domain.enums.MedicalCarePriority;

public record NewMedicalCareDTO(
        String document,
        Short age,
        MedicalCarePriority priority,
        String description,
        String aiJustification,
        Boolean priorityAccess,
        String phoneNumber
) {}
