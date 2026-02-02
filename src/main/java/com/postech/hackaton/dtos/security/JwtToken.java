package com.postech.hackaton.dtos.security;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtToken {
    private String token;
    private String type;
    private Long expiresIn;
}