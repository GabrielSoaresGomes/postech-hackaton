package com.postech.hackaton.infrastructure.data_sources;

import com.postech.hackaton.infrastructure.entities.ScreeningItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ScreeningItemRepository extends JpaRepository<ScreeningItemEntity, Long> {
    List<ScreeningItemEntity> findByScreeningId(Long screeningId);
}