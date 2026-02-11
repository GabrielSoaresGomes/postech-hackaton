package com.postech.hackaton.application.usecases.medical_care;

import com.postech.hackaton.application.gateways.MedicalCareGateway;
import com.postech.hackaton.application.gateways.EmailGateway;
import com.postech.hackaton.application.usecases.UseCase;
import com.postech.hackaton.domain.MedicalCare;
import com.postech.hackaton.domain.enums.MedicalCareStatus;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class PrioritizeMedicalCareUseCase implements UseCase<Long, MedicalCare> {
    private final MedicalCareGateway medicalCareGateway;
    private final EmailGateway emailGateway;

    public PrioritizeMedicalCareUseCase(MedicalCareGateway medicalCareGateway, EmailGateway emailGateway) {
        this.medicalCareGateway = medicalCareGateway;
        this.emailGateway = emailGateway;
    }

    @Override
    public MedicalCare execute(Long id) {
        log.info("Buscando atendimento com id  {} para priorização" , id);
        var medicalCare = this.medicalCareGateway.findById(id);

        log.info("Atendimento encontrado. Priorizando atendimento");
        var prioritizedMedicalCare = medicalCare.withStatus(MedicalCareStatus.IN_PROGRESS);

        var updated = this.medicalCareGateway.update(prioritizedMedicalCare, id);

        var phone = medicalCare.getPhoneNumber();
        if(Objects.nonNull(phone)) {
            this.sendMessage(phone);
        }

        return updated;
    }

    private void sendMessage(String phoneNumber) {
        var subject = "Seu atendimento foi priorizado";
        var body = "Olá, seu atendimento foi priorizado e está em andamento, se dirija a recepção.";
        try {
            emailGateway.send(phoneNumber, subject, body);
        } catch (Exception e) {
            log.warn("Não foi possível enviar email de notificação: {}", e.getMessage());
        }
    }
}
