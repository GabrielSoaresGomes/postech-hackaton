package com.postech.hackaton.infrastructure.data_sources.user;

import com.postech.hackaton.infrastructure.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByLogin(String login);
    Optional<UserEntity> findByEmail(String email);
}