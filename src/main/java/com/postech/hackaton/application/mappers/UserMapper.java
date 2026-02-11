package com.postech.hackaton.application.mappers;

import com.postech.hackaton.domain.User;
import com.postech.hackaton.dtos.requests.user.CreateUserRequestDTO;
import com.postech.hackaton.dtos.transfer.user.NewUserDTO;
import com.postech.hackaton.dtos.transfer.user.UserDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper {
    public static User toUser(UserDTO dto) {
        return new User(
                dto.id(),
                dto.userType(),
                dto.name(),
                dto.email(),
                dto.login(),
                dto.password(),
                dto.lastModifiedDateTime()
        );
    }
    public static User toUser(CreateUserRequestDTO dto) {
        return new User(
                dto.userType(),
                dto.name(),
                dto.email(),
                dto.login(),
                dto.password()
        );
    }

    public static NewUserDTO toNewUserDTO(User user) {
        return new NewUserDTO(
                user.getUserType(),
                user.getName(),
                user.getEmail(),
                user.getLogin(),
                user.getPassword()
        );
    }

    public static UserDTO toUserDto(User entity) {
        return new UserDTO(
                entity.getId(),
                entity.getUserType(),
                entity.getName(),
                entity.getEmail(),
                entity.getLogin(),
                entity.getPassword(),
                entity.getLastModifiedDateTime(),
                entity.getLastModifiedDateTime()
        );
    }
}
