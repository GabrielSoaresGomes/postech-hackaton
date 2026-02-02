package com.postech.hackaton.dtos.responses;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private Long id;
    private String userType;
    private String name;
    private String email;
    private String login;
    private LocalDateTime lastModifiedDateTime;
}