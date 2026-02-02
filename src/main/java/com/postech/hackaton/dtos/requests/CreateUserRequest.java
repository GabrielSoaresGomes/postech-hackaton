package com.postech.hackaton.dtos.requests;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserRequest {
    private String userType;
    private String name;
    private String email;
    private String login;
    private String password;
}