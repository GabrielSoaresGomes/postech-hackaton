package com.postech.hackaton.dtos.transfer.user;

import java.time.LocalDateTime;

public record UserDTO(
        Long id,
        String userType,
        String name,
        String email,
        String login,
        String password,
        LocalDateTime lastModifiedDateTime,
        LocalDateTime createdAt
) {
}
