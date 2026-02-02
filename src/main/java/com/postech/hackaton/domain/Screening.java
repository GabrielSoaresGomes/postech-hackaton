package com.postech.hackaton.domain;

import com.postech.hackaton.domain.enums.ScreeningPriority;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode
@ToString
public class Screening {
    private Long id;
    private Long patientId;
    private Long healthProfessionalId;
    private String description;
    private ScreeningPriority priority;
    private Boolean completed;
    private String aiAssessment;
    private LocalDateTime createdAt;
    private LocalDateTime completedAt;
    private LocalDateTime lastModifiedDateTime;

    public Screening(Long id, @NonNull Long patientId, Long healthProfessionalId, @NonNull String description,
                     @NonNull ScreeningPriority priority, @NonNull Boolean completed, String aiAssessment,
                     @NonNull LocalDateTime createdAt, LocalDateTime completedAt, @NonNull LocalDateTime lastModifiedDateTime) {
        this.id = id;
        this.patientId = patientId;
        this.healthProfessionalId = healthProfessionalId;
        this.description = description;
        this.priority = priority;
        this.completed = completed;
        this.aiAssessment = aiAssessment;
        this.createdAt = createdAt;
        this.completedAt = completedAt;
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

    public Screening(@NonNull Long patientId, @NonNull String description, @NonNull ScreeningPriority priority) {
        this(null, patientId, null, description, priority, false, null, LocalDateTime.now(), null, LocalDateTime.now());
    }
}