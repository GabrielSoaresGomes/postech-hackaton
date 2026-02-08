package com.postech.hackaton.application.gateways;

import com.postech.hackaton.dtos.transfer.ai_triage.AiTriageRequestDTO;
import com.postech.hackaton.dtos.transfer.ai_triage.AiTriageResponseDTO;

public interface AiTriageGateway {
    AiTriageResponseDTO classify(AiTriageRequestDTO request);
}
