package com.postech.hackaton.infrastructure.data_sources.medical_care;

import com.postech.hackaton.infrastructure.entities.MedicalCareEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalCareJpaRepository extends JpaRepository<MedicalCareEntity, Long> {
}
