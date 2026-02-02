package com.postech.hackaton.infrastructure.data_sources;

import com.postech.hackaton.infrastructure.entities.ScreeningResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ScreeningResultRepository extends JpaRepository<ScreeningResultEntity, Long> {
    Optional<ScreeningResultEntity> findByScreeningId(Long screeningId);
}