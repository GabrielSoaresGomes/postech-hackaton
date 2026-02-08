package com.postech.hackaton.application.mappers;

import com.postech.hackaton.domain.MedicalCare;
import com.postech.hackaton.dtos.requests.medical_care.CreateMedicalCareRequestDTO;
import com.postech.hackaton.dtos.transfer.medical_care.MedicalCareDTO;
import com.postech.hackaton.dtos.transfer.medical_care.NewMedicalCareDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MedicalCareMapper {
    public static MedicalCare toMedicalCare(MedicalCareDTO dto) {
        return new MedicalCare(
                dto.id(),
                dto.document(),
                dto.age(),
                dto.priority(),
                dto.description(),
                dto.aiJustification(),
                dto.priorityAccess(),
                dto.phoneNumber(),
                dto.status(),
                dto.createdAt(),
                dto.lastModifiedAt(),
                dto.deletedAt()
        );
    }
    public static MedicalCare toMedicalCare(CreateMedicalCareRequestDTO dto) {
        return new MedicalCare(
                dto.document(),
                dto.age(),
                dto.description(),
                dto.priorityAccess(),
                dto.phoneNumber()
        );
    }

    public static NewMedicalCareDTO toNewMedicalCareDTO(MedicalCare medicalCare) {
        return new NewMedicalCareDTO(
                medicalCare.getDocument(),
                medicalCare.getAge(),
                medicalCare.getDescription(),
                medicalCare.getPriorityAccess(),
                medicalCare.getPhoneNumber()
        );
    }

    public static MedicalCareDTO toMedicalCareDto(MedicalCare entity) {
        return new MedicalCareDTO(
                entity.getId(),
                entity.getDocument(),
                entity.getAge(),
                entity.getPriority(),
                entity.getDescription(),
                entity.getAiJustification(),
                entity.getPriorityAccess(),
                entity.getPhoneNumber(),
                entity.getStatus(),
                entity.getCreatedAt(),
                entity.getLastModifiedAt(),
                entity.getDeletedAt()
        );
    }
}
