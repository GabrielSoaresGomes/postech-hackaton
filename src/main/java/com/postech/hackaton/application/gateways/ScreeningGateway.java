package com.postech.hackaton.application.gateways;

import com.postech.hackaton.domain.Screening;
import java.util.List;
import java.util.Optional;

public interface ScreeningGateway {
    Screening create(Screening screening);
    Optional<Screening> findById(Long id);
    List<Screening> findByPatientId(Long patientId);
    List<Screening> findByHealthProfessionalId(Long healthProfessionalId);
    List<Screening> findByCompleted(Boolean completed);
    Screening update(Screening screening);
    void delete(Long id);
}