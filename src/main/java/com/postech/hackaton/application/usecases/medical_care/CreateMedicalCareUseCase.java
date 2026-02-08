package com.postech.hackaton.application.usecases.medical_care;

import com.postech.hackaton.application.gateways.AiTriageGateway;
import com.postech.hackaton.application.gateways.MedicalCareGateway;
import com.postech.hackaton.application.mappers.MedicalCareMapper;
import com.postech.hackaton.application.services.ai_triage.AiTriageApplier;
import com.postech.hackaton.application.services.ai_triage.AiTriageDataExtractor;
import com.postech.hackaton.application.services.ai_triage.AiTriagePromptFactory;
import com.postech.hackaton.application.usecases.UseCase;
import com.postech.hackaton.domain.MedicalCare;
import com.postech.hackaton.dtos.requests.medical_care.CreateMedicalCareRequestDTO;
import com.postech.hackaton.dtos.transfer.ai_triage.AiTriageRequestDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateMedicalCareUseCase implements UseCase<CreateMedicalCareRequestDTO, MedicalCare> {

    private final MedicalCareGateway medicalCareGateway;
    private final AiTriageGateway aiTriageGateway;

    private final AiTriageDataExtractor dataExtractor;
    private final AiTriagePromptFactory promptFactory;
    private final AiTriageApplier applier;

    public CreateMedicalCareUseCase(
            MedicalCareGateway medicalCareGateway,
            AiTriageGateway aiTriageGateway,
            AiTriageDataExtractor dataExtractor,
            AiTriagePromptFactory promptFactory,
            AiTriageApplier applier
    ) {
        this.medicalCareGateway = medicalCareGateway;
        this.aiTriageGateway = aiTriageGateway;
        this.dataExtractor = dataExtractor;
        this.promptFactory = promptFactory;
        this.applier = applier;
    }

    @Override
    public MedicalCare execute(CreateMedicalCareRequestDTO request) {
        var medicalCare = MedicalCareMapper.toMedicalCare(request);

        try {
            var triageData = dataExtractor.extract(request, medicalCare);
            var prompt = promptFactory.buildPrompt(triageData);

            var ai = aiTriageGateway.classify(new AiTriageRequestDTO(triageData, prompt));
            medicalCare = applier.apply(medicalCare, ai);

            log.info("Triagem IA aplicada: priority={}, confidence={}", ai.priority(), ai.confidence());
        } catch (Exception e) {
            // Não bloqueia criação se IA falhar
            log.warn("Falha na triagem IA. Prosseguindo sem IA. Motivo: {}", e.getMessage());
        }

        log.info("Criando novo atendimento {}", medicalCare);
        var savedMedicalCare = this.medicalCareGateway.save(medicalCare);

        log.info("Atendimento criado {}", savedMedicalCare);
        return savedMedicalCare;
    }
}
