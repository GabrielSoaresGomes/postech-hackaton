package com.postech.hackaton.domain;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode
@ToString
public class User {
    private final Long id;
    private final String userType;
    private final String name;
    private final String email;
    private final String login;
    private final String password;
    private final LocalDateTime lastModifiedDateTime;

    public User(Long id, @NonNull String userType, @NonNull String name, String email,
                @NonNull String login, @NonNull String password, LocalDateTime lastModifiedDateTime) {
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