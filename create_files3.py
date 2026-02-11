#!/usr/bin/env python3

import os

base_path = "/Users/brunobender/git/postech-hackaton/src/main/java/com/postech/hackaton"

files_to_create = {
    # Infrastructure - Data Sources (Repositories)
    f"{base_path}/infrastructure/data_sources/UserRepository.java": '''package com.postech.hackaton.infrastructure.data_sources;

import com.postech.hackaton.infrastructure.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByLogin(String login);
    Optional<UserEntity> findByEmail(String email);
}''',

    f"{base_path}/infrastructure/data_sources/UserAddressRepository.java": '''package com.postech.hackaton.infrastructure.data_sources;

import com.postech.hackaton.infrastructure.entities.UserAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserAddressRepository extends JpaRepository<UserAddressEntity, Long> {
    List<UserAddressEntity> findByUserId(Long userId);
}''',

    f"{base_path}/infrastructure/data_sources/ScreeningRepository.java": '''package com.postech.hackaton.infrastructure.data_sources;

import com.postech.hackaton.infrastructure.entities.ScreeningEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ScreeningRepository extends JpaRepository<ScreeningEntity, Long> {
    List<ScreeningEntity> findByPatientId(Long patientId);
    List<ScreeningEntity> findByHealthProfessionalId(Long healthProfessionalId);
    List<ScreeningEntity> findByCompleted(Boolean completed);
}''',

    f"{base_path}/infrastructure/data_sources/ScreeningItemRepository.java": '''package com.postech.hackaton.infrastructure.data_sources;

import com.postech.hackaton.infrastructure.entities.ScreeningItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ScreeningItemRepository extends JpaRepository<ScreeningItemEntity, Long> {
    List<ScreeningItemEntity> findByScreeningId(Long screeningId);
}''',

    f"{base_path}/infrastructure/data_sources/ScreeningResultRepository.java": '''package com.postech.hackaton.infrastructure.data_sources;

import com.postech.hackaton.infrastructure.entities.ScreeningResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ScreeningResultRepository extends JpaRepository<ScreeningResultEntity, Long> {
    Optional<ScreeningResultEntity> findByScreeningId(Long screeningId);
}''',

    # Infrastructure - Mappers
    f"{base_path}/infrastructure/mappers/UserMapper.java": '''package com.postech.hackaton.infrastructure.mappers;

import com.postech.hackaton.domain.User;
import com.postech.hackaton.dtos.responses.UserResponse;
import com.postech.hackaton.infrastructure.entities.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toDomain(UserEntity entity) {
        if (entity == null) return null;
        return new User(entity.getId(), entity.getUserType(), entity.getName(),
                entity.getEmail(), entity.getLogin(), entity.getPassword(), entity.getLastModifiedDateTime());
    }

    public UserEntity toEntity(User domain) {
        if (domain == null) return null;
        return UserEntity.builder()
                .id(domain.getId())
                .userType(domain.getUserType())
                .name(domain.getName())
                .email(domain.getEmail())
                .login(domain.getLogin())
                .password(domain.getPassword())
                .lastModifiedDateTime(domain.getLastModifiedDateTime())
                .build();
    }

    public UserResponse toResponse(User domain) {
        if (domain == null) return null;
        return UserResponse.builder()
                .id(domain.getId())
                .userType(domain.getUserType())
                .name(domain.getName())
                .email(domain.getEmail())
                .login(domain.getLogin())
                .lastModifiedDateTime(domain.getLastModifiedDateTime())
                .build();
    }
}''',

    f"{base_path}/infrastructure/mappers/ScreeningMapper.java": '''package com.postech.hackaton.infrastructure.mappers;

import com.postech.hackaton.domain.Screening;
import com.postech.hackaton.dtos.responses.ScreeningResponse;
import com.postech.hackaton.infrastructure.entities.ScreeningEntity;
import org.springframework.stereotype.Component;

@Component
public class ScreeningMapper {
    public Screening toDomain(ScreeningEntity entity) {
        if (entity == null) return null;
        return new Screening(entity.getId(), entity.getPatientId(), entity.getHealthProfessionalId(),
                entity.getDescription(), entity.getPriority(), entity.getCompleted(), entity.getAiAssessment(),
                entity.getCreatedAt(), entity.getCompletedAt(), entity.getLastModifiedDateTime());
    }

    public ScreeningEntity toEntity(Screening domain) {
        if (domain == null) return null;
        return ScreeningEntity.builder()
                .id(domain.getId())
                .patientId(domain.getPatientId())
                .healthProfessionalId(domain.getHealthProfessionalId())
                .description(domain.getDescription())
                .priority(domain.getPriority())
                .completed(domain.getCompleted())
                .aiAssessment(domain.getAiAssessment())
                .createdAt(domain.getCreatedAt())
                .completedAt(domain.getCompletedAt())
                .lastModifiedDateTime(domain.getLastModifiedDateTime())
                .build();
    }

    public ScreeningResponse toResponse(Screening domain) {
        if (domain == null) return null;
        return ScreeningResponse.builder()
                .id(domain.getId())
                .patientId(domain.getPatientId())
                .healthProfessionalId(domain.getHealthProfessionalId())
                .description(domain.getDescription())
                .priority(domain.getPriority())
                .completed(domain.getCompleted())
                .aiAssessment(domain.getAiAssessment())
                .createdAt(domain.getCreatedAt())
                .completedAt(domain.getCompletedAt())
                .build();
    }
}''',

    # Application - Gateways
    f"{base_path}/application/gateways/UserGateway.java": '''package com.postech.hackaton.application.gateways;

import com.postech.hackaton.domain.User;
import java.util.Optional;

public interface UserGateway {
    User create(User user);
    Optional<User> findById(Long id);
    Optional<User> findByLogin(String login);
    Optional<User> findByEmail(String email);
    void delete(Long id);
}''',

    f"{base_path}/application/gateways/ScreeningGateway.java": '''package com.postech.hackaton.application.gateways;

import com.postech.hackaton.domain.Screening;
import java.util.List;
import java.util.Optional;

public interface ScreeningGateway {
    Screening create(Screening screening);
    Optional<Screening> findById(Long id);
    List<Screening> findByPatientId(Long patientId);
    List<Screening> findByHealthProfessionalId(Long healthProfessionalId);
    List<Screening> findByCompleted(Boolean completed);
    Screening update(Screening screening);
    void delete(Long id);
}''',

    # Interface Adapter - Gateways Implementation
    f"{base_path}/interface_adapter/gateways/UserGatewayImpl.java": '''package com.postech.hackaton.interface_adapter.gateways;

import com.postech.hackaton.domain.User;
import com.postech.hackaton.application.gateways.UserGateway;
import com.postech.hackaton.infrastructure.data_sources.user.UserJpaRepository;
import com.postech.hackaton.infrastructure.mappers.UserEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserGatewayImpl implements UserGateway {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User create(User user) {
        var entity = userMapper.toEntity(user);
        var savedEntity = userRepository.save(entity);
        return userMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id).map(userMapper::toDomain);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return userRepository.findByLogin(login).map(userMapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email).map(userMapper::toDomain);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}''',

    f"{base_path}/interface_adapter/gateways/ScreeningGatewayImpl.java": '''package com.postech.hackaton.interface_adapter.gateways;

import com.postech.hackaton.domain.Screening;
import com.postech.hackaton.application.gateways.ScreeningGateway;
import com.postech.hackaton.infrastructure.data_sources.ScreeningRepository;
import com.postech.hackaton.infrastructure.mappers.ScreeningMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ScreeningGatewayImpl implements ScreeningGateway {
    private final ScreeningRepository screeningRepository;
    private final ScreeningMapper screeningMapper;

    @Override
    public Screening create(Screening screening) {
        var entity = screeningMapper.toEntity(screening);
        var savedEntity = screeningRepository.save(entity);
        return screeningMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Screening> findById(Long id) {
        return screeningRepository.findById(id).map(screeningMapper::toDomain);
    }

    @Override
    public List<Screening> findByPatientId(Long patientId) {
        return screeningRepository.findByPatientId(patientId).stream()
                .map(screeningMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Screening> findByHealthProfessionalId(Long healthProfessionalId) {
        return screeningRepository.findByHealthProfessionalId(healthProfessionalId).stream()
                .map(screeningMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Screening> findByCompleted(Boolean completed) {
        return screeningRepository.findByCompleted(completed).stream()
                .map(screeningMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public Screening update(Screening screening) {
        var entity = screeningMapper.toEntity(screening);
        var updatedEntity = screeningRepository.save(entity);
        return screeningMapper.toDomain(updatedEntity);
    }

    @Override
    public void delete(Long id) {
        screeningRepository.deleteById(id);
    }
}''',

    # Application - Use Cases
    f"{base_path}/application/usecases/CreateUserUseCase.java": '''package com.postech.hackaton.application.usecases;

import com.postech.hackaton.domain.User;
import com.postech.hackaton.exceptions.UserAlreadyExistsException;
import com.postech.hackaton.exceptions.InvalidEmailException;
import com.postech.hackaton.application.gateways.UserGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserUseCase {
    private final UserGateway userGateway;
    private final PasswordEncoder passwordEncoder;

    public User execute(User user) {
        if (user.getEmail() != null && !isValidEmail(user.getEmail())) {
            throw new InvalidEmailException("Invalid email format");
        }

        if (userGateway.findByLogin(user.getLogin()).isPresent()) {
            throw new UserAlreadyExistsException("User with login already exists");
        }

        if (user.getEmail() != null && userGateway.findByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("User with email already exists");
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        User newUser = new User(user.getUserType(), user.getName(), user.getEmail(),
                user.getLogin(), encodedPassword);

        return userGateway.create(newUser);
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
}''',

    f"{base_path}/application/usecases/CreateScreeningUseCase.java": '''package com.postech.hackaton.application.usecases;

import com.postech.hackaton.domain.Screening;
import com.postech.hackaton.application.gateways.ScreeningGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateScreeningUseCase {
    private final ScreeningGateway screeningGateway;

    public Screening execute(Screening screening) {
        if (screening.getPatientId() == null) {
            throw new IllegalArgumentException("Patient ID is required");
        }

        if (screening.getDescription() == null || screening.getDescription().trim().isEmpty()) {
            throw new IllegalArgumentException("Description is required");
        }

        if (screening.getPriority() == null) {
            throw new IllegalArgumentException("Priority is required");
        }

        return screeningGateway.create(screening);
    }
}''',

    f"{base_path}/application/usecases/GetScreeningUseCase.java": '''package com.postech.hackaton.application.usecases;

import com.postech.hackaton.domain.Screening;
import com.postech.hackaton.exceptions.ScreeningNotFoundException;
import com.postech.hackaton.application.gateways.ScreeningGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetScreeningUseCase {
    private final ScreeningGateway screeningGateway;

    public Screening execute(Long screeningId) {
        if (screeningId == null || screeningId <= 0) {
            throw new IllegalArgumentException("Invalid screening ID");
        }

        return screeningGateway.findById(screeningId)
                .orElseThrow(() -> new ScreeningNotFoundException("Screening not found"));
    }
}''',

    # Infrastructure - Controllers
    f"{base_path}/infrastructure/controllers/AuthController.java": '''package com.postech.hackaton.infrastructure.controllers;

import com.postech.hackaton.dtos.requests.LoginRequest;
import com.postech.hackaton.dtos.requests.user.CreateUserRequestDTO;
import com.postech.hackaton.dtos.responses.UserResponse;
import com.postech.hackaton.dtos.security.JwtToken;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Endpoints for user authentication")
public class AuthController {

    @PostMapping("/login")
    @Operation(summary = "User login", description = "Authenticate user with login and password")
    public ResponseEntity<JwtToken> login(@RequestBody LoginRequest request) {
        JwtToken token = JwtToken.builder()
                .token("mock-token")
                .type("Bearer")
                .expiresIn(3600000L)
                .build();
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    @Operation(summary = "Register new user", description = "Create a new user account")
    public ResponseEntity<UserResponse> register(@RequestBody CreateUserRequest request) {
        UserResponse response = UserResponse.builder()
                .id(1L)
                .userType(request.getUserType())
                .name(request.getName())
                .email(request.getEmail())
                .login(request.getLogin())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}''',

    f"{base_path}/infrastructure/controllers/ScreeningController.java": '''package com.postech.hackaton.infrastructure.controllers;

import com.postech.hackaton.dtos.requests.CreateScreeningRequest;
import com.postech.hackaton.dtos.responses.ScreeningResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/screenings")
@Tag(name = "Screenings", description = "Endpoints for screening management")
@SecurityRequirement(name = "bearerAuth")
public class ScreeningController {

    @GetMapping
    @Operation(summary = "List all screenings")
    public ResponseEntity<List<ScreeningResponse>> listAll() {
        return ResponseEntity.ok(new ArrayList<>());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get screening by ID")
    public ResponseEntity<ScreeningResponse> getById(@PathVariable Long id) {
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Operation(summary = "Create new screening")
    public ResponseEntity<ScreeningResponse> create(@RequestBody CreateScreeningRequest request) {
        ScreeningResponse response = ScreeningResponse.builder()
                .id(1L)
                .patientId(request.getPatientId())
                .description(request.getDescription())
                .priority(request.getPriority())
                .completed(false)
                .createdAt(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update screening")
    public ResponseEntity<ScreeningResponse> update(@PathVariable Long id, @RequestBody CreateScreeningRequest request) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete screening")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }
}''',

    f"{base_path}/infrastructure/controllers/UserController.java": '''package com.postech.hackaton.infrastructure.controllers;

import com.postech.hackaton.dtos.responses.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/users")
@Tag(name = "Users", description = "Endpoints for user management")
@SecurityRequirement(name = "bearerAuth")
public class UserController {

    @GetMapping
    @Operation(summary = "List all users")
    public ResponseEntity<List<UserResponse>> listAll() {
        return ResponseEntity.ok(new ArrayList<>());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID")
    public ResponseEntity<UserResponse> getById(@PathVariable Long id) {
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }
}''',

    # Test
    f"{base_path}/HackatonApplicationTests.java": '''package com.postech.hackaton;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HackatonApplicationTests {
    @Test
    void contextLoads() {
    }
}''',

    # Repositories Interface
    f"{base_path}/repositories/CrudRepository.java": '''package com.postech.hackaton.repositories;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, ID> {
    T save(T entity);
    Optional<T> findById(ID id);
    List<T> findAll();
    void delete(ID id);
    void deleteAll();
    long count();
}''',
}

for file_path, content in files_to_create.items():
    directory = os.path.dirname(file_path)
    os.makedirs(directory, exist_ok=True)
    with open(file_path, 'w') as f:
        f.write(content)
    print(f"Created: {file_path}")

print("All files created successfully!")

