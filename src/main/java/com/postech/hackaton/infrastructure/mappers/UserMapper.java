package com.postech.hackaton.infrastructure.mappers;

import com.postech.hackaton.domain.User;
import com.postech.hackaton.dtos.responses.UserResponse;
import com.postech.hackaton.infrastructure.entities.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toDomain(UserEntity entity) {
        if (entity == null) return null;
        return new User(entity.getId(), entity.getUserType(), entity.getName(),
                entity.getEmail(), entity.getLogin(), entity.getPassword(), entity.getLastModifiedDateTime());
    }

    public UserEntity toEntity(User domain) {
        if (domain == null) return null;
        return UserEntity.builder()
                .id(domain.getId())
                .userType(domain.getUserType())
                .name(domain.getName())
                .email(domain.getEmail())
                .login(domain.getLogin())
                .password(domain.getPassword())
                .lastModifiedDateTime(domain.getLastModifiedDateTime())
                .build();
    }

    public UserResponse toResponse(User domain) {
        if (domain == null) return null;
        return UserResponse.builder()
                .id(domain.getId())
                .userType(domain.getUserType())
                .name(domain.getName())
                .email(domain.getEmail())
                .login(domain.getLogin())
                .lastModifiedDateTime(domain.getLastModifiedDateTime())
                .build();
    }
}