package com.postech.hackaton.infrastructure.mappers;

import com.postech.hackaton.domain.User;
import com.postech.hackaton.dtos.transfer.user.NewUserDTO;
import com.postech.hackaton.dtos.transfer.user.UserDTO;
import com.postech.hackaton.infrastructure.entities.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserEntityMapper {
    public static UserEntity toUserEntity(NewUserDTO dto) {
        return new UserEntity(
                null,
                dto.userType(),
                dto.name(),
                dto.email(),
                dto.login(),
                dto.password(),
                null,
                null
        );
    }

    public static UserEntity toUserEntity(UserDTO dto) {
        return new UserEntity(
                dto.id(),
                dto.userType(),
                dto.name(),
                dto.email(),
                dto.login(),
                dto.password(),
                dto.lastModifiedDateTime(),
                dto.createdAt()
        );
    }

    public NewUserDTO toNewUserDTO(User user) {
        return new NewUserDTO(
                user.getUserType(),
                user.getName(),
                user.getEmail(),
                user.getLogin(),
                user.getPassword()
        );
    }


    public static UserDTO toUserDTO(UserEntity entity) {
        return new UserDTO(
                entity.getId(),
                entity.getUserType(),
                entity.getName(),
                entity.getEmail(),
                entity.getLogin(),
                entity.getPassword(),
                entity.getLastModifiedDateTime(),
                entity.getCreatedAt()
        );
    }
}