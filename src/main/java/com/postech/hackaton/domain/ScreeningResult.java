package com.postech.hackaton.domain;

import com.postech.hackaton.domain.enums.ScreeningPriority;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode
@ToString
public class ScreeningResult {
    private Long id;
    private Long screeningId;
    private ScreeningPriority recommendedPriority;
    private String aiAnalysis;
    private Double confidenceScore;
    private String recommendations;
    private LocalDateTime generatedAt;
    private LocalDateTime lastModifiedDateTime;

    public ScreeningResult(Long id, @NonNull Long screeningId, @NonNull ScreeningPriority recommendedPriority,
                          String aiAnalysis, @NonNull Double confidenceScore, String recommendations,
                          @NonNull LocalDateTime generatedAt, @NonNull LocalDateTime lastModifiedDateTime) {
        this.id = id;
        this.screeningId = screeningId;
        this.recommendedPriority = recommendedPriority;
        this.aiAnalysis = aiAnalysis;
        this.confidenceScore = confidenceScore;
        this.recommendations = recommendations;
        this.generatedAt = generatedAt;
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

    public ScreeningResult(@NonNull Long screeningId, @NonNull ScreeningPriority recommendedPriority,
                          String aiAnalysis, @NonNull Double confidenceScore, String recommendations) {
        this(null, screeningId, recommendedPriority, aiAnalysis, confidenceScore, recommendations, LocalDateTime.now(), LocalDateTime.now());
    }
}