package com.postech.hackaton.interface_adapter.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.postech.hackaton.application.gateways.AiTriageGateway;
import com.postech.hackaton.application.services.ai_triage.AiTriageApplier;
import com.postech.hackaton.application.services.ai_triage.AiTriageDataExtractor;
import com.postech.hackaton.application.services.ai_triage.AiTriagePromptFactory;
import com.postech.hackaton.application.usecases.medical_care.CreateMedicalCareUseCase;
import com.postech.hackaton.application.usecases.medical_care.FindMedicalCareByIdUseCase;
import com.postech.hackaton.application.usecases.medical_care.ListMedicalCareUseCase;
import com.postech.hackaton.application.usecases.medical_care.PrioritizeMedicalCareUseCase;
import com.postech.hackaton.application.usecases.medical_care.PrioritizeMedicalCareUseCase;
import com.postech.hackaton.dtos.requests.medical_care.CreateMedicalCareRequestDTO;
import com.postech.hackaton.dtos.requests.medical_care.ListMedicalCareRequestDTO;
import com.postech.hackaton.dtos.responses.medical_care.MedicalCareResponseDTO;
import com.postech.hackaton.interface_adapter.data_sources.repositories.MedicalCareRepository;
import com.postech.hackaton.interface_adapter.gateways.MedicalCareGatewayImpl;
import com.postech.hackaton.interface_adapter.presenters.MedicalCarePresenter;

import java.util.List;

public class MedicalCareController {
    private final ListMedicalCareUseCase listMedicalCareUseCase;
    private final FindMedicalCareByIdUseCase findMedicalCareByIdUseCase;
    private final CreateMedicalCareUseCase createMedicalCareUseCase;
    private final PrioritizeMedicalCareUseCase prioritizeMedicalCareUseCase;

    public MedicalCareController(
            MedicalCareRepository repository,
            AiTriageGateway aiTriageGateway,
            ObjectMapper objectMapper
    ) {
        var medicalCareGateway = new MedicalCareGatewayImpl(repository);

        this.listMedicalCareUseCase = new ListMedicalCareUseCase(medicalCareGateway);
        this.findMedicalCareByIdUseCase = new FindMedicalCareByIdUseCase(medicalCareGateway);
        this.prioritizeMedicalCareUseCase = new PrioritizeMedicalCareUseCase(medicalCareGateway);

        var extractor = new AiTriageDataExtractor();
        var promptFactory = new AiTriagePromptFactory(objectMapper);
        var applier = new AiTriageApplier();

        this.createMedicalCareUseCase = new CreateMedicalCareUseCase(
                medicalCareGateway,
                aiTriageGateway,
                extractor,
                promptFactory,
                applier
        );
    }

    public List<MedicalCareResponseDTO> list(ListMedicalCareRequestDTO request) {
        var medicalCares = this.listMedicalCareUseCase.execute(request);

        return MedicalCarePresenter.medicalCareListToMedicalCareResponseDTOList(medicalCares);
    }

    public MedicalCareResponseDTO find(Long id) {
        var createdMedicalCare = this.findMedicalCareByIdUseCase.execute(id);

        return MedicalCarePresenter.medicalCareToMedicalCareResponseDTO(createdMedicalCare);
    }

    public MedicalCareResponseDTO create(CreateMedicalCareRequestDTO request) {
        var createdMedicalCare = this.createMedicalCareUseCase.execute(request);

        return MedicalCarePresenter.medicalCareToMedicalCareResponseDTO(createdMedicalCare);
    }

    public MedicalCareResponseDTO prioritize(Long id) {
        var prioritizedMedicalCare = this.prioritizeMedicalCareUseCase.execute(id);

        return MedicalCarePresenter.medicalCareToMedicalCareResponseDTO(prioritizedMedicalCare);
    }
}
