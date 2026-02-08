package com.postech.hackaton.infrastructure.mappers;

import com.postech.hackaton.domain.enums.MedicalCarePriority;
import com.postech.hackaton.domain.enums.MedicalCareStatus;
import com.postech.hackaton.dtos.transfer.medical_care.MedicalCareDTO;
import com.postech.hackaton.dtos.transfer.medical_care.NewMedicalCareDTO;
import com.postech.hackaton.infrastructure.entities.MedicalCareEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MedicalCareEntityMapper {
    public static MedicalCareEntity toMedicalCareEntity(NewMedicalCareDTO dto) {
        return new MedicalCareEntity(
                null,
                dto.document(),
                dto.age(),
                MedicalCarePriority.P5,
                dto.description(),
                null,
                dto.priorityAccess(),
                dto.phoneNumber(),
                MedicalCareStatus.PENDING,
                LocalDateTime.now(),
                LocalDateTime.now(),
                null
        );
    }

    public static MedicalCareEntity toMedicalCareEntity(MedicalCareDTO dto) {
        return new MedicalCareEntity(
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

    public static MedicalCareDTO toMedicalCareDTO(MedicalCareEntity entity) {
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
