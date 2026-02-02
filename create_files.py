#!/usr/bin/env python3

import os
import json

base_path = "/Users/brunobender/git/postech-hackaton/src/main/java/com/postech/hackaton"

files_to_create = {
    # Application Layer
    f"{base_path}/HackatonApplication.java": '''package com.postech.hackaton;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HackatonApplication {
    public static void main(String[] args) {
        SpringApplication.run(HackatonApplication.class, args);
    }
}''',

    # Domain Enums
    f"{base_path}/domain/enums/UserType.java": '''package com.postech.hackaton.domain.enums;

public enum UserType {
    ADMIN, HEALTH_PROFESSIONAL, PATIENT
}''',

    f"{base_path}/domain/enums/ScreeningPriority.java": '''package com.postech.hackaton.domain.enums;

public enum ScreeningPriority {
    CRITICAL, HIGH, MEDIUM, LOW
}''',

    # Domain Classes
    f"{base_path}/domain/User.java": '''package com.postech.hackaton.domain;

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
}''',

    f"{base_path}/domain/UserAddress.java": '''package com.postech.hackaton.domain;

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
}''',

    f"{base_path}/domain/Screening.java": '''package com.postech.hackaton.domain;

import com.postech.hackaton.domain.enums.ScreeningPriority;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode
@ToString
public class Screening {
    private Long id;
    private Long patientId;
    private Long healthProfessionalId;
    private String description;
    private ScreeningPriority priority;
    private Boolean completed;
    private String aiAssessment;
    private LocalDateTime createdAt;
    private LocalDateTime completedAt;
    private LocalDateTime lastModifiedDateTime;

    public Screening(Long id, @NonNull Long patientId, Long healthProfessionalId, @NonNull String description,
                     @NonNull ScreeningPriority priority, @NonNull Boolean completed, String aiAssessment,
                     @NonNull LocalDateTime createdAt, LocalDateTime completedAt, @NonNull LocalDateTime lastModifiedDateTime) {
        this.id = id;
        this.patientId = patientId;
        this.healthProfessionalId = healthProfessionalId;
        this.description = description;
        this.priority = priority;
        this.completed = completed;
        this.aiAssessment = aiAssessment;
        this.createdAt = createdAt;
        this.completedAt = completedAt;
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

    public Screening(@NonNull Long patientId, @NonNull String description, @NonNull ScreeningPriority priority) {
        this(null, patientId, null, description, priority, false, null, LocalDateTime.now(), null, LocalDateTime.now());
    }
}''',

    f"{base_path}/domain/ScreeningItem.java": '''package com.postech.hackaton.domain;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode
@ToString
public class ScreeningItem {
    private Long id;
    private Long screeningId;
    private String question;
    private String answer;
    private String category;
    private LocalDateTime lastModifiedDateTime;

    public ScreeningItem(Long id, @NonNull Long screeningId, @NonNull String question, String answer,
                        @NonNull String category, @NonNull LocalDateTime lastModifiedDateTime) {
        this.id = id;
        this.screeningId = screeningId;
        this.question = question;
        this.answer = answer;
        this.category = category;
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

    public ScreeningItem(@NonNull Long screeningId, @NonNull String question, String answer, @NonNull String category) {
        this(null, screeningId, question, answer, category, LocalDateTime.now());
    }
}''',

    f"{base_path}/domain/ScreeningResult.java": '''package com.postech.hackaton.domain;

import com.postech.hackaton.domain.enums.ScreeningPriority;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode
@ToString
public class ScreeningResult {
    private Long id;
    private Long screeningId;
    private ScreeningPriority recommendedPriority;
    private String aiAnalysis;
    private Double confidenceScore;
    private String recommendations;
    private LocalDateTime generatedAt;
    private LocalDateTime lastModifiedDateTime;

    public ScreeningResult(Long id, @NonNull Long screeningId, @NonNull ScreeningPriority recommendedPriority,
                          String aiAnalysis, @NonNull Double confidenceScore, String recommendations,
                          @NonNull LocalDateTime generatedAt, @NonNull LocalDateTime lastModifiedDateTime) {
        this.id = id;
        this.screeningId = screeningId;
        this.recommendedPriority = recommendedPriority;
        this.aiAnalysis = aiAnalysis;
        this.confidenceScore = confidenceScore;
        this.recommendations = recommendations;
        this.generatedAt = generatedAt;
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

    public ScreeningResult(@NonNull Long screeningId, @NonNull ScreeningPriority recommendedPriority,
                          String aiAnalysis, @NonNull Double confidenceScore, String recommendations) {
        this(null, screeningId, recommendedPriority, aiAnalysis, confidenceScore, recommendations, LocalDateTime.now(), LocalDateTime.now());
    }
}''',

    # Exceptions
    f"{base_path}/exceptions/BusinessException.java": '''package com.postech.hackaton.exceptions;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) { super(message); }
    public BusinessException(String message, Throwable cause) { super(message, cause); }
}''',

    f"{base_path}/exceptions/ResourceNotFoundException.java": '''package com.postech.hackaton.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) { super(message); }
    public ResourceNotFoundException(String message, Throwable cause) { super(message, cause); }
}''',

    f"{base_path}/exceptions/UserNotFoundException.java": '''package com.postech.hackaton.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) { super(message); }
    public UserNotFoundException(String message, Throwable cause) { super(message, cause); }
}''',

    f"{base_path}/exceptions/UserAlreadyExistsException.java": '''package com.postech.hackaton.exceptions;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message) { super(message); }
    public UserAlreadyExistsException(String message, Throwable cause) { super(message, cause); }
}''',

    f"{base_path}/exceptions/InvalidEmailException.java": '''package com.postech.hackaton.exceptions;

public class InvalidEmailException extends RuntimeException {
    public InvalidEmailException(String message) { super(message); }
    public InvalidEmailException(String message, Throwable cause) { super(message, cause); }
}''',

    f"{base_path}/exceptions/ScreeningNotFoundException.java": '''package com.postech.hackaton.exceptions;

public class ScreeningNotFoundException extends RuntimeException {
    public ScreeningNotFoundException(String message) { super(message); }
    public ScreeningNotFoundException(String message, Throwable cause) { super(message, cause); }
}''',

    f"{base_path}/exceptions/UnauthorizedException.java": '''package com.postech.hackaton.exceptions;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) { super(message); }
    public UnauthorizedException(String message, Throwable cause) { super(message, cause); }
}''',
}

for file_path, content in files_to_create.items():
    directory = os.path.dirname(file_path)
    os.makedirs(directory, exist_ok=True)
    with open(file_path, 'w') as f:
        f.write(content)
    print(f"Created: {file_path}")

print("All files created successfully!")

