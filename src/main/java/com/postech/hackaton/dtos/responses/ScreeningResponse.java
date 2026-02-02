package com.postech.hackaton.dtos.responses;

import com.postech.hackaton.domain.enums.ScreeningPriority;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScreeningResponse {
    private Long id;
    private Long patientId;
    private Long healthProfessionalId;
    private String description;
    private ScreeningPriority priority;
    private Boolean completed;
    private String aiAssessment;
    private LocalDateTime createdAt;
    private LocalDateTime completedAt;
}