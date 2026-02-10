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

        log.info("Atendimento encontrado. Priorizando atendimento");
        var prioritizedMedicalCare = medicalCare.withStatus(MedicalCareStatus.IN_PROGRESS);

        return this.medicalCareGateway.update(prioritizedMedicalCare, id);
    }
}
