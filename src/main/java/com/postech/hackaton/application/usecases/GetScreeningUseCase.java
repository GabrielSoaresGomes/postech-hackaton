package com.postech.hackaton.application.usecases;

import com.postech.hackaton.domain.Screening;
import com.postech.hackaton.exceptions.ScreeningNotFoundException;
import com.postech.hackaton.application.gateways.ScreeningGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetScreeningUseCase {
    private final ScreeningGateway screeningGateway;

    public Screening execute(Long screeningId) {
        if (screeningId == null || screeningId <= 0) {
            throw new IllegalArgumentException("Invalid screening ID");
        }

        return screeningGateway.findById(screeningId)
                .orElseThrow(() -> new ScreeningNotFoundException("Screening not found"));
    }
}