package com.postech.hackaton.application.services.ai_triage;

import com.postech.hackaton.domain.MedicalCare;
import com.postech.hackaton.domain.enums.MedicalCarePriority;
import com.postech.hackaton.dtos.transfer.ai_triage.AiTriageResponseDTO;

public class AiTriageApplier {

    public MedicalCare apply(MedicalCare original, AiTriageResponseDTO ai) {
        MedicalCarePriority priority = parsePriority(ai.priority());

        return new MedicalCare(
                original.getId(),
                original.getDocument(),
                original.getAge(),
                priority,
                original.getDescription(),
                ai.rationale(), // aiJustification
                original.getPriorityAccess(),
                original.getPhoneNumber(),
                original.getStatus(),
                original.getCreatedAt(),
                original.getLastModifiedAt(),
                original.getDeletedAt()
        );
    }

    private MedicalCarePriority parsePriority(String p) {
        if (p == null) return MedicalCarePriority.P5;
        return switch (p.trim().toUpperCase()) {
            case "P1" -> MedicalCarePriority.P1;
            case "P2" -> MedicalCarePriority.P2;
            case "P3" -> MedicalCarePriority.P3;
            case "P4" -> MedicalCarePriority.P4;
            case "P5" -> MedicalCarePriority.P5;
            default -> MedicalCarePriority.P5;
        };
    }
}
