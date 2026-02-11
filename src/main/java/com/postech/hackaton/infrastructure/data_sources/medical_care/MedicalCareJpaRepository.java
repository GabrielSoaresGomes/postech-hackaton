package com.postech.hackaton.infrastructure.data_sources.medical_care;

import com.postech.hackaton.infrastructure.entities.MedicalCareEntity;
import com.postech.hackaton.domain.enums.MedicalCareStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalCareJpaRepository extends JpaRepository<MedicalCareEntity, Long> {
    Page<MedicalCareEntity> findByPriorityAccessAndStatus(Boolean priorityAccess, MedicalCareStatus status, Pageable pageable);
}
