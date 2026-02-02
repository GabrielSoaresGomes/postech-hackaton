package com.postech.hackaton.domain;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode
@ToString
public class UserAddress {
    private Long id;
    private Long userId;
    private String street;
    private String number;
    private String complement;
    private String city;
    private String state;
    private String zipCode;
    private LocalDateTime lastModifiedDateTime;

    public UserAddress(Long id, @NonNull Long userId, @NonNull String street, @NonNull String number,
                       String complement, @NonNull String city, @NonNull String state, @NonNull String zipCode,
                       @NonNull LocalDateTime lastModifiedDateTime) {
        this.id = id;
        this.userId = userId;
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

    public UserAddress(@NonNull Long userId, @NonNull String street, @NonNull String number,
                       String complement, @NonNull String city, @NonNull String state, @NonNull String zipCode) {
        this(null, userId, street, number, complement, city, state, zipCode, LocalDateTime.now());
    }
}