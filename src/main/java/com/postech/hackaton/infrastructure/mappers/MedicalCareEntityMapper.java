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
                dto.priority() != null ? dto.priority() : MedicalCarePriority.P5,
                dto.description(),
                dto.aiJustification(),
                dto.priorityAccess(),
                dto.phoneNumber(),
                MedicalCareStatus.IN_PROGRESS,
                LocalDateTime.now(),
                LocalDateTime.now(),
                null
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
