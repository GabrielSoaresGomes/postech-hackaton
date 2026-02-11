package com.postech.hackaton.application.usecases.medical_care;

import com.postech.hackaton.application.gateways.MedicalCareGateway;
import com.postech.hackaton.application.usecases.UseCase;
import com.postech.hackaton.domain.MedicalCare;
import com.postech.hackaton.dtos.requests.medical_care.UpdateMedicalCareRequestDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UpdateMedicalCareUseCase implements UseCase<UpdateMedicalCareRequestDTO, MedicalCare> {

    private final MedicalCareGateway medicalCareGateway;

    public UpdateMedicalCareUseCase(
            MedicalCareGateway medicalCareGateway
    ) {
        this.medicalCareGateway = medicalCareGateway;
    }

    @Override
    public MedicalCare execute(UpdateMedicalCareRequestDTO request) {
        log.info("Atualizando atendimento id={}", request.id());

        var existing = this.medicalCareGateway.findById(request.id());

        var newPriority = request.data().priority();
        var newPriorityAccess = request.data().priorityAccess();

        var updated = existing;

        if (newPriority != null) {
            updated = updated.withPriority(newPriority);
            log.debug("Priority atualizado para {}", newPriority);
        }

        if (newPriorityAccess != null) {
            updated = updated.withPriorityAccess(newPriorityAccess);
            log.debug("PriorityAccess atualizado para {}", newPriorityAccess);
        }

        var persisted = this.medicalCareGateway.update(updated, request.id());

        log.info("Atendimento atualizado id={} -> {}", request.id(), persisted);
        return persisted;
    }
}
