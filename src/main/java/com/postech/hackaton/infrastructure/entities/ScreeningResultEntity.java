package com.postech.hackaton.infrastructure.entities;

import com.postech.hackaton.domain.enums.ScreeningPriority;
import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "screening_results")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScreeningResultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "screening_id", nullable = false)
    private Long screeningId;

    @Enumerated(EnumType.STRING)
    @Column(name = "recommended_priority", nullable = false)
    private ScreeningPriority recommendedPriority;

    @Column(name = "ai_analysis", columnDefinition = "TEXT")
    private String aiAnalysis;

    @Column(name = "confidence_score")
    private Double confidenceScore;

    @Column(columnDefinition = "TEXT")
    private String recommendations;

    @Column(name = "generated_at")
    private LocalDateTime generatedAt;

    @Column(name = "last_modified_date_time")
    private LocalDateTime lastModifiedDateTime;
}