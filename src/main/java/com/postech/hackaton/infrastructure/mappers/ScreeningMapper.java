package com.postech.hackaton.infrastructure.mappers;

import com.postech.hackaton.domain.Screening;
import com.postech.hackaton.dtos.responses.ScreeningResponse;
import com.postech.hackaton.infrastructure.entities.ScreeningEntity;
import org.springframework.stereotype.Component;

@Component
public class ScreeningMapper {
    public Screening toDomain(ScreeningEntity entity) {
        if (entity == null) return null;
        return new Screening(entity.getId(), entity.getPatientId(), entity.getHealthProfessionalId(),
                entity.getDescription(), entity.getPriority(), entity.getCompleted(), entity.getAiAssessment(),
                entity.getCreatedAt(), entity.getCompletedAt(), entity.getLastModifiedDateTime());
    }

    public ScreeningEntity toEntity(Screening domain) {
        if (domain == null) return null;
        return ScreeningEntity.builder()
                .id(domain.getId())
                .patientId(domain.getPatientId())
                .healthProfessionalId(domain.getHealthProfessionalId())
                .description(domain.getDescription())
                .priority(domain.getPriority())
                .completed(domain.getCompleted())
                .aiAssessment(domain.getAiAssessment())
                .createdAt(domain.getCreatedAt())
                .completedAt(domain.getCompletedAt())
                .lastModifiedDateTime(domain.getLastModifiedDateTime())
                .build();
    }

    public ScreeningResponse toResponse(Screening domain) {
        if (domain == null) return null;
        return ScreeningResponse.builder()
                .id(domain.getId())
                .patientId(domain.getPatientId())
                .healthProfessionalId(domain.getHealthProfessionalId())
                .description(domain.getDescription())
                .priority(domain.getPriority())
                .completed(domain.getCompleted())
                .aiAssessment(domain.getAiAssessment())
                .createdAt(domain.getCreatedAt())
                .completedAt(domain.getCompletedAt())
                .build();
    }
}