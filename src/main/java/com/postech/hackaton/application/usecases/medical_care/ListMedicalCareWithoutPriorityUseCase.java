package com.postech.hackaton.application.usecases.medical_care;

import com.postech.hackaton.application.gateways.MedicalCareGateway;
import com.postech.hackaton.application.usecases.UseCase;
import com.postech.hackaton.domain.MedicalCare;
import com.postech.hackaton.dtos.requests.medical_care.ListMedicalCareRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

@Slf4j
public class ListMedicalCareWithoutPriorityUseCase implements UseCase<ListMedicalCareRequestDTO, List<MedicalCare>> {
    private final MedicalCareGateway medicalCareGateway;

    public ListMedicalCareWithoutPriorityUseCase(MedicalCareGateway medicalCareGateway) {
        this.medicalCareGateway = medicalCareGateway;
    }

    @Override
    public List<MedicalCare> execute(ListMedicalCareRequestDTO request) {
        log.info("Listando atendimentos");
        Sort sortByPriority = Sort.by(Sort.Direction.ASC, "priority");
        return this.medicalCareGateway.findAll(PageRequest.of(request.page(), request.size(), sortByPriority), Boolean.FALSE);
    }
}
