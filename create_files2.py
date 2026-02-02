#!/usr/bin/env python3

import os

base_path = "/Users/brunobender/git/postech-hackaton/src/main/java/com/postech/hackaton"

files_to_create = {
    # DTOs
    f"{base_path}/dtos/common/ApiErrorResponse.java": '''package com.postech.hackaton.dtos.common;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiErrorResponse {
    private Integer statusCode;
    private String message;
    private String details;
    private Long timestamp;
}''',

    f"{base_path}/dtos/security/JwtToken.java": '''package com.postech.hackaton.dtos.security;

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
}''',

    f"{base_path}/dtos/requests/LoginRequest.java": '''package com.postech.hackaton.dtos.requests;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequest {
    private String login;
    private String password;
}''',

    f"{base_path}/dtos/requests/CreateUserRequest.java": '''package com.postech.hackaton.dtos.requests;

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
}''',

    f"{base_path}/dtos/requests/CreateScreeningRequest.java": '''package com.postech.hackaton.dtos.requests;

import com.postech.hackaton.domain.enums.ScreeningPriority;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateScreeningRequest {
    private Long patientId;
    private String description;
    private ScreeningPriority priority;
}''',

    f"{base_path}/dtos/responses/UserResponse.java": '''package com.postech.hackaton.dtos.responses;

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
}''',

    f"{base_path}/dtos/responses/ScreeningResponse.java": '''package com.postech.hackaton.dtos.responses;

import com.postech.hackaton.domain.enums.ScreeningPriority;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScreeningResponse {
    private Long id;
    private Long patientId;
    private Long healthProfessionalId;
    private String description;
    private ScreeningPriority priority;
    private Boolean completed;
    private String aiAssessment;
    private LocalDateTime createdAt;
    private LocalDateTime completedAt;
}''',

    # Infrastructure - Config
    f"{base_path}/infrastructure/config/SecurityConfig.java": '''package com.postech.hackaton.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .anyRequest().permitAll());
        return http.build();
    }
}''',

    f"{base_path}/infrastructure/config/OpenApiConfig.java": '''package com.postech.hackaton.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Hackaton PosTech - Triagem Inteligente com IA")
                        .version("1.0.0")
                        .description("API para gerenciamento de triagem inteligente com IA")
                        .contact(new Contact().name("PosTech").url("https://www.postech.com.br")))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .description("JWT Token")))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
    }
}''',

    # Infrastructure - Entities
    f"{base_path}/infrastructure/entities/UserEntity.java": '''package com.postech.hackaton.infrastructure.entities;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userType;

    @Column(nullable = false)
    private String name;

    @Column(unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String password;

    @Column(name = "last_modified_date_time")
    private LocalDateTime lastModifiedDateTime;
}''',

    f"{base_path}/infrastructure/entities/UserAddressEntity.java": '''package com.postech.hackaton.infrastructure.entities;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_addresses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String number;

    @Column
    private String complement;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(name = "zip_code", nullable = false)
    private String zipCode;

    @Column(name = "last_modified_date_time")
    private LocalDateTime lastModifiedDateTime;
}''',

    f"{base_path}/infrastructure/entities/ScreeningEntity.java": '''package com.postech.hackaton.infrastructure.entities;

import com.postech.hackaton.domain.enums.ScreeningPriority;
import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "screenings")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScreeningEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "patient_id", nullable = false)
    private Long patientId;

    @Column(name = "health_professional_id")
    private Long healthProfessionalId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ScreeningPriority priority;

    @Column(nullable = false)
    private Boolean completed;

    @Column(name = "ai_assessment", columnDefinition = "TEXT")
    private String aiAssessment;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;

    @Column(name = "last_modified_date_time")
    private LocalDateTime lastModifiedDateTime;
}''',

    f"{base_path}/infrastructure/entities/ScreeningItemEntity.java": '''package com.postech.hackaton.infrastructure.entities;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "screening_items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScreeningItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "screening_id", nullable = false)
    private Long screeningId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String question;

    @Column(columnDefinition = "TEXT")
    private String answer;

    @Column(nullable = false)
    private String category;

    @Column(name = "last_modified_date_time")
    private LocalDateTime lastModifiedDateTime;
}''',

    f"{base_path}/infrastructure/entities/ScreeningResultEntity.java": '''package com.postech.hackaton.infrastructure.entities;

import com.postech.hackaton.domain.enums.ScreeningPriority;
import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "screening_results")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScreeningResultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "screening_id", nullable = false)
    private Long screeningId;

    @Enumerated(EnumType.STRING)
    @Column(name = "recommended_priority", nullable = false)
    private ScreeningPriority recommendedPriority;

    @Column(name = "ai_analysis", columnDefinition = "TEXT")
    private String aiAnalysis;

    @Column(name = "confidence_score")
    private Double confidenceScore;

    @Column(columnDefinition = "TEXT")
    private String recommendations;

    @Column(name = "generated_at")
    private LocalDateTime generatedAt;

    @Column(name = "last_modified_date_time")
    private LocalDateTime lastModifiedDateTime;
}''',
}

for file_path, content in files_to_create.items():
    directory = os.path.dirname(file_path)
    os.makedirs(directory, exist_ok=True)
    with open(file_path, 'w') as f:
        f.write(content)
    print(f"Created: {file_path}")

print("All files created successfully!")

