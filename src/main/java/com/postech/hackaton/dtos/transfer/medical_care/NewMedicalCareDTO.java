package com.postech.hackaton.dtos.transfer.medical_care;

public record NewMedicalCareDTO(
        String document,
        Short age,
        String description,
        Boolean priorityAccess,
        String phoneNumber
) {
}
