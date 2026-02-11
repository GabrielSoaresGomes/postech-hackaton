package com.postech.hackaton.dtos.requests.medical_care;

import com.postech.hackaton.domain.enums.MedicalCarePriority;
import jakarta.validation.constraints.NotNull;

public record UpdateMedicalCareDataRequestDTO(
        @NotNull(message = "A prioridade não pode ser nula")
        MedicalCarePriority priority,
        @NotNull(message = "O acesso prioritário não pode ser nulo")
        Boolean priorityAccess
) {
}
