package com.postech.hackaton.application.usecases.medical_care;

import com.postech.hackaton.application.gateways.MedicalCareGateway;
import com.postech.hackaton.application.usecases.UseCase;
import com.postech.hackaton.domain.MedicalCare;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FindMedicalCareByIdUseCase implements UseCase<Long, MedicalCare> {
    private final MedicalCareGateway medicalCareGateway;

    public FindMedicalCareByIdUseCase(MedicalCareGateway medicalCareGateway) {
        this.medicalCareGateway = medicalCareGateway;
    }

    @Override
    public MedicalCare execute(Long id) {
        log.info("Buscando atendimento com id {}", id);
        var medicalCare = this.medicalCareGateway.findById(id);

        log.info("Atendimento encontrado {}", medicalCare);
        return medicalCare;
    }
}
