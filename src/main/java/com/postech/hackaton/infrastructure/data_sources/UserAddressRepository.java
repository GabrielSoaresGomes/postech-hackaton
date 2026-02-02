package com.postech.hackaton.infrastructure.data_sources;

import com.postech.hackaton.infrastructure.entities.UserAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserAddressRepository extends JpaRepository<UserAddressEntity, Long> {
    List<UserAddressEntity> findByUserId(Long userId);
}