package com.postech.hackaton.infrastructure.entities;

import com.postech.hackaton.domain.enums.MedicalCarePriority;
import com.postech.hackaton.domain.enums.MedicalCareStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "medical_care")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedicalCareEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "document", nullable = false)
    private String document;

    @Column(name = "age", nullable = false)
    private Short age;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority", nullable = false)
    private MedicalCarePriority priority;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "ai_justification")
    private String aiJustification;

    @Column(name = "priority_access", nullable = false)
    private Boolean priorityAccess;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private MedicalCareStatus status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "last_modified_at")
    private LocalDateTime lastModifiedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
}
