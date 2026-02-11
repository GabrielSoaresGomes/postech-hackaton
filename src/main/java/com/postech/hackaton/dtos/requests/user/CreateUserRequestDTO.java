package com.postech.hackaton.dtos.requests.user;

public record CreateUserRequestDTO (
    String userType,
    String name,
    String email,
    String login,
    String password
){}