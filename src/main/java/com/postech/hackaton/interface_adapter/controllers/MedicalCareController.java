package com.postech.hackaton.interface_adapter.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.postech.hackaton.application.gateways.AiTriageGateway;
import com.postech.hackaton.application.gateways.EmailGateway;
import com.postech.hackaton.application.services.ai_triage.AiTriageApplier;
import com.postech.hackaton.application.services.ai_triage.AiTriageDataExtractor;
import com.postech.hackaton.application.services.ai_triage.AiTriagePromptFactory;
import com.postech.hackaton.application.usecases.medical_care.*;
import com.postech.hackaton.dtos.requests.medical_care.CreateMedicalCareRequestDTO;
import com.postech.hackaton.dtos.requests.medical_care.ListMedicalCareRequestDTO;
import com.postech.hackaton.dtos.requests.medical_care.UpdateMedicalCareRequestDTO;
import com.postech.hackaton.dtos.responses.medical_care.MedicalCareResponseDTO;
import com.postech.hackaton.interface_adapter.data_sources.repositories.MedicalCareRepository;
import com.postech.hackaton.interface_adapter.gateways.MedicalCareGatewayImpl;
import com.postech.hackaton.interface_adapter.presenters.MedicalCarePresenter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MedicalCareController {
    private final ListMedicalCareWithoutPriorityUseCase listMedicalCareWithoutPriorityUseCase;
    private final ListMedicalCareWithPriorityUseCase listMedicalCareWithPriorityUseCase;
    private final FindMedicalCareByIdUseCase findMedicalCareByIdUseCase;
    private final CreateMedicalCareUseCase createMedicalCareUseCase;
    private final UpdateMedicalCareUseCase updateMedicalCareUseCase;
    private final PrioritizeMedicalCareUseCase prioritizeMedicalCareUseCase;

    public MedicalCareController(
            MedicalCareRepository repository,
            AiTriageGateway aiTriageGateway,
            ObjectMapper objectMapper,
            EmailGateway emailGateway
    ) {
        var medicalCareGateway = new MedicalCareGatewayImpl(repository);

        this.listMedicalCareWithoutPriorityUseCase = new ListMedicalCareWithoutPriorityUseCase(medicalCareGateway);
        this.listMedicalCareWithPriorityUseCase = new ListMedicalCareWithPriorityUseCase(medicalCareGateway);
        this.findMedicalCareByIdUseCase = new FindMedicalCareByIdUseCase(medicalCareGateway);
        this.prioritizeMedicalCareUseCase = new PrioritizeMedicalCareUseCase(medicalCareGateway, emailGateway);
        this.updateMedicalCareUseCase = new UpdateMedicalCareUseCase(medicalCareGateway);
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

    public List<MedicalCareResponseDTO> listWithoutPriority(ListMedicalCareRequestDTO request) {
        var medicalCares = this.listMedicalCareWithoutPriorityUseCase.execute(request);

        return MedicalCarePresenter.medicalCareListToMedicalCareResponseDTOList(medicalCares);
    }

    public List<MedicalCareResponseDTO> listWithPriority(ListMedicalCareRequestDTO request) {
        var medicalCares = this.listMedicalCareWithPriorityUseCase.execute(request);

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
    public MedicalCareResponseDTO update(UpdateMedicalCareRequestDTO request) {
        var updatedMedicalCare = this.updateMedicalCareUseCase.execute(request);

        return MedicalCarePresenter.medicalCareToMedicalCareResponseDTO(updatedMedicalCare);
    }

    public MedicalCareResponseDTO prioritize(Long id) {
        var prioritizedMedicalCare = this.prioritizeMedicalCareUseCase.execute(id);

        return MedicalCarePresenter.medicalCareToMedicalCareResponseDTO(prioritizedMedicalCare);
    }
}
