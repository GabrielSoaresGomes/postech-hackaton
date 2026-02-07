package com.postech.hackaton.application.usecases.medical_care;

import com.postech.hackaton.application.gateways.MedicalCareGateway;
import com.postech.hackaton.application.usecases.UseCase;
import com.postech.hackaton.domain.MedicalCare;
import com.postech.hackaton.dtos.requests.medical_care.ListMedicalCareRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@Slf4j
public class ListMedicalCareUseCase implements UseCase<ListMedicalCareRequestDTO, List<MedicalCare>> {
    private final MedicalCareGateway medicalCareGateway;

    public ListMedicalCareUseCase(MedicalCareGateway medicalCareGateway) {
        this.medicalCareGateway = medicalCareGateway;
    }

    @Override
    public List<MedicalCare> execute(ListMedicalCareRequestDTO request) {
        log.info("Listando atendimentos");
        return this.medicalCareGateway.findAll(PageRequest.of(request.page(), request.size()));
    }
}
