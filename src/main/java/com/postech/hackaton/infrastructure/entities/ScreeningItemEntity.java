package com.postech.hackaton.infrastructure.entities;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "screening_items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScreeningItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "screening_id", nullable = false)
    private Long screeningId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String question;

    @Column(columnDefinition = "TEXT")
    private String answer;

    @Column(nullable = false)
    private String category;

    @Column(name = "last_modified_date_time")
    private LocalDateTime lastModifiedDateTime;
}