package com.postech.hackaton.application.usecases;

import com.postech.hackaton.domain.Screening;
import com.postech.hackaton.application.gateways.ScreeningGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateScreeningUseCase {
    private final ScreeningGateway screeningGateway;

    public Screening execute(Screening screening) {
        if (screening.getPatientId() == null) {
            throw new IllegalArgumentException("Patient ID is required");
        }

        if (screening.getDescription() == null || screening.getDescription().trim().isEmpty()) {
            throw new IllegalArgumentException("Description is required");
        }

        if (screening.getPriority() == null) {
            throw new IllegalArgumentException("Priority is required");
        }

        return screeningGateway.create(screening);
    }
}