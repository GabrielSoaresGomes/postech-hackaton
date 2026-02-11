package com.postech.hackaton.dtos.transfer.user;

import com.postech.hackaton.domain.enums.UserType;

public record NewUserDTO(
        String userType,
        String name,
        String email,
        String login,
        String password
) {}
