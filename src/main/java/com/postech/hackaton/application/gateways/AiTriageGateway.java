package com.postech.hackaton.application.gateways;

import com.postech.hackaton.dtos.transfer.AiTriageRequest;
import com.postech.hackaton.dtos.transfer.AiTriageResponse;

public interface AiTriageGateway {
    AiTriageResponse classify(AiTriageRequest request);
}
