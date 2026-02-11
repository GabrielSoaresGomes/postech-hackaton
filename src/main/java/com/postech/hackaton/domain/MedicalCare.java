package com.postech.hackaton.domain;

import com.postech.hackaton.domain.enums.MedicalCarePriority;
import com.postech.hackaton.domain.enums.MedicalCareStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode
@ToString
public class MedicalCare {
    private final Long id;
    private final String document;
    private final Short age;
    private final MedicalCarePriority priority;
    private final String description;
    private final String aiJustification;
    private final Boolean priorityAccess;
    private final String phoneNumber;
    private final MedicalCareStatus status;
    private final LocalDateTime createdAt;
    private final LocalDateTime lastModifiedAt;
    private final LocalDateTime deletedAt;

    public MedicalCare(
            Long id,
            @NonNull String document,
            @NonNull Short age,
            MedicalCarePriority priority,
            @NonNull String description,
            String aiJustification,
            @NonNull Boolean priorityAccess,
            @NonNull String phoneNumber,
            MedicalCareStatus status,
            LocalDateTime createdAt,
            LocalDateTime lastModifiedAt,
            LocalDateTime deletedAt
    ) {
        this.id = id;
        this.document = document;
        this.age = age;
        this.priority = priority;
        this.description = description;
        this.aiJustification = aiJustification;
        this.priorityAccess = priorityAccess;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.createdAt = createdAt;
        this.lastModifiedAt = lastModifiedAt;
        this.deletedAt = deletedAt;
    }

    public MedicalCare(
            @NonNull String document,
            @NonNull Short age,
            @NonNull String description,
            @NonNull Boolean priorityAccess,
            @NonNull String phoneNumber
    ) {
        this(
                null,
                document,
                age,
                null,
                description,
                null,
                priorityAccess,
                phoneNumber,
                null,
                LocalDateTime.now(),
                LocalDateTime.now(),
                null
        );
    }

    public MedicalCare withStatus(@NonNull MedicalCareStatus newStatus) {
        return new MedicalCare(
                this.id,
                this.document,
                this.age,
                this.priority,
                this.description,
                this.aiJustification,
                this.priorityAccess,
                this.phoneNumber,
                newStatus,
                this.createdAt,
                LocalDateTime.now(),
                this.deletedAt
        );
    }

    public MedicalCare withPriority(MedicalCarePriority newPriority) {
        return new MedicalCare(
                this.id,
                this.document,
                this.age,
                newPriority,
                this.description,
                this.aiJustification,
                this.priorityAccess,
                this.phoneNumber,
                this.status,
                this.createdAt,
                LocalDateTime.now(),
                this.deletedAt
        );
    }

    public MedicalCare withPriorityAccess(@NonNull Boolean newPriorityAccess) {
        return new MedicalCare(
                this.id,
                this.document,
                this.age,
                this.priority,
                this.description,
                this.aiJustification,
                newPriorityAccess,
                this.phoneNumber,
                this.status,
                this.createdAt,
                LocalDateTime.now(),
                this.deletedAt
        );
    }
}
