package com.postech.hackaton.infrastructure.entities;

import com.postech.hackaton.domain.enums.ScreeningPriority;
import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "screenings")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScreeningEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "patient_id", nullable = false)
    private Long patientId;

    @Column(name = "health_professional_id")
    private Long healthProfessionalId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ScreeningPriority priority;

    @Column(nullable = false)
    private Boolean completed;

    @Column(name = "ai_assessment", columnDefinition = "TEXT")
    private String aiAssessment;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;

    @Column(name = "last_modified_date_time")
    private LocalDateTime lastModifiedDateTime;
}