package com.postech.hackaton.application.usecases.medical_care;

import com.postech.hackaton.application.gateways.MedicalCareGateway;
import com.postech.hackaton.application.mappers.MedicalCareMapper;
import com.postech.hackaton.application.usecases.UseCase;
import com.postech.hackaton.domain.MedicalCare;
import com.postech.hackaton.dtos.requests.medical_care.CreateMedicalCareRequestDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateMedicalCareUseCase implements UseCase<CreateMedicalCareRequestDTO, MedicalCare> {
    private final MedicalCareGateway medicalCareGateway;

    public CreateMedicalCareUseCase(MedicalCareGateway medicalCareGateway) {
        this.medicalCareGateway = medicalCareGateway;
    }

    @Override
    public MedicalCare execute(CreateMedicalCareRequestDTO request) {
        var medicalCare = MedicalCareMapper.toMedicalCare(request);

        log.info("Criando novo atendimento {}", medicalCare);
        var savedMedicalCare = this.medicalCareGateway.save(medicalCare);

        log.info("Atendimento criado {}", savedMedicalCare);
        return savedMedicalCare;
    }
}
