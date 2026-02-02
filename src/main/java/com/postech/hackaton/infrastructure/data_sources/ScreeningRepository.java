package com.postech.hackaton.infrastructure.data_sources;

import com.postech.hackaton.infrastructure.entities.ScreeningEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ScreeningRepository extends JpaRepository<ScreeningEntity, Long> {
    List<ScreeningEntity> findByPatientId(Long patientId);
    List<ScreeningEntity> findByHealthProfessionalId(Long healthProfessionalId);
    List<ScreeningEntity> findByCompleted(Boolean completed);
}