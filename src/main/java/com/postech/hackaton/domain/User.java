package com.postech.hackaton.domain;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode
@ToString
public class User {
    private Long id;
    private String userType;
    private String name;
    private String email;
    private String login;
    private String password;
    private LocalDateTime lastModifiedDateTime;

    public User(Long id, @NonNull String userType, @NonNull String name, String email,
                @NonNull String login, @NonNull String password, @NonNull LocalDateTime lastModifiedDateTime) {
        this.id = id;
        this.userType = userType;
        this.name = name;
        this.email = email;
        this.login = login;
        this.password = password;
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

    public User(@NonNull String userType, @NonNull String name, String email,
                @NonNull String login, @NonNull String password) {
        this(null, userType, name, email, login, password, LocalDateTime.now());
    }
}