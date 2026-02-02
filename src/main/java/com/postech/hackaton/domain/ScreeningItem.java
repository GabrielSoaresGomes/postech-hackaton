package com.postech.hackaton.domain;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode
@ToString
public class ScreeningItem {
    private Long id;
    private Long screeningId;
    private String question;
    private String answer;
    private String category;
    private LocalDateTime lastModifiedDateTime;

    public ScreeningItem(Long id, @NonNull Long screeningId, @NonNull String question, String answer,
                        @NonNull String category, @NonNull LocalDateTime lastModifiedDateTime) {
        this.id = id;
        this.screeningId = screeningId;
        this.question = question;
        this.answer = answer;
        this.category = category;
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

    public ScreeningItem(@NonNull Long screeningId, @NonNull String question, String answer, @NonNull String category) {
        this(null, screeningId, question, answer, category, LocalDateTime.now());
    }
}