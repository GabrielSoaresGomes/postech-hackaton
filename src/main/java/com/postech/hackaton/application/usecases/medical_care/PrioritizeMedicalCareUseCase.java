package com.postech.hackaton.application.usecases.medical_care;

import com.postech.hackaton.application.gateways.MedicalCareGateway;
import com.postech.hackaton.application.usecases.UseCase;
import com.postech.hackaton.domain.MedicalCare;
import com.postech.hackaton.domain.enums.MedicalCareStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PrioritizeMedicalCareUseCase implements UseCase<Long, MedicalCare> {
    private final MedicalCareGateway medicalCareGateway;

    public PrioritizeMedicalCareUseCase(MedicalCareGateway medicalCareGateway) {
        this.medicalCareGateway = medicalCareGateway;
    }

    @Override
    public MedicalCare execute(Long id) {
        log.info("Buscando atendimento com id  {} para priorização" , id);
        var medicalCare = this.medicalCareGateway.findById(id);

        if (medicalCare == null) {
            log.error("Atendimento não encontrado para o id {}", id);
            throw new RuntimeException("Atendimento não encontrado para o id %d".formatted(id));
        }

        log.info("Atendimento encontrado. Priorizando atendimento");
        var prioritizedMedicalCare = medicalCare.withStatus(MedicalCareStatus.IN_PROGRESS);
        log.info("aqui está o atendimento priorizado: {}", prioritizedMedicalCare);
        this.medicalCareGateway.update(prioritizedMedicalCare, id);

        return prioritizedMedicalCare;
    }
}
