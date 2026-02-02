package com.postech.hackaton.dtos.requests;

import com.postech.hackaton.domain.enums.ScreeningPriority;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateScreeningRequest {
    private Long patientId;
    private String description;
    private ScreeningPriority priority;
}