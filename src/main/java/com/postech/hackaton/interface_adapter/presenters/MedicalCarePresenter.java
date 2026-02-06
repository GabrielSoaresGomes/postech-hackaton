package com.postech.hackaton.interface_adapter.presenters;

import com.postech.hackaton.domain.MedicalCare;
import com.postech.hackaton.dtos.responses.medical_care.MedicalCareResponseDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MedicalCarePresenter {
    public static MedicalCareResponseDTO medicalCareToMedicalCareResponseDTO(MedicalCare medicalCare) {
        return new MedicalCareResponseDTO(
                medicalCare.getId(),
                medicalCare.getDocument(),
                medicalCare.getAge(),
                medicalCare.getPriority(),
                medicalCare.getDescription(),
                medicalCare.getAiJustification(),
                medicalCare.getPriorityAccess(),
                medicalCare.getPhoneNumber(),
                medicalCare.getStatus()
        );
    }

    public static List<MedicalCareResponseDTO> medicalCareListToMedicalCareResponseDTOList(List<MedicalCare> medicalCares) {
        return Optional.ofNullable(medicalCares)
                .map(
                        mcs -> mcs.stream()
                                .map(MedicalCarePresenter::medicalCareToMedicalCareResponseDTO)
                                .toList()
                )
                .orElse(null);
    }
}
