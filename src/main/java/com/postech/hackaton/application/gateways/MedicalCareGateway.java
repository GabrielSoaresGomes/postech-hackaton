package com.postech.hackaton.application.gateways;

import com.postech.hackaton.domain.MedicalCare;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MedicalCareGateway extends CrudGateway<MedicalCare, Long> {
    List<MedicalCare> findAll(Pageable pageable, Boolean priorityAccess);
}
