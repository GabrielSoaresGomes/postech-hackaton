package com.postech.hackaton.application.usecases.user;

import com.postech.hackaton.application.usecases.UseCase;
import com.postech.hackaton.domain.User;
import com.postech.hackaton.dtos.requests.user.CreateUserRequestDTO;
import com.postech.hackaton.exceptions.InvalidEmailException;
import com.postech.hackaton.application.gateways.UserGateway;
import com.postech.hackaton.exceptions.UserAlreadyExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
public class CreateUserUseCase implements UseCase<CreateUserRequestDTO, User> {
    private final UserGateway userGateway;
    private final PasswordEncoder passwordEncoder;

    public CreateUserUseCase(UserGateway userGateway, PasswordEncoder passwordEncoder) {
        this.userGateway = userGateway;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User execute(CreateUserRequestDTO request) {
        if (request.email() != null && !isValidEmail(request.email())) {
            log.error("Endereço de email inválido: {}", request.email());
            throw new UserAlreadyExistsException(HttpStatus.BAD_REQUEST, "Email inválido");
        }

        if (userGateway.findByLogin(request.login()).isPresent()) {
            log.error("Login já existente: {}", request.login());
            throw new UserAlreadyExistsException(HttpStatus.BAD_REQUEST, "User with login already exists");
        }

        if (request.email() != null && userGateway.findByEmail(request.email()).isPresent()) {
            log.error("Email já existente: {}", request.email());
            throw new UserAlreadyExistsException(HttpStatus.BAD_REQUEST, "Email existente");
        }

        String encodedPassword = passwordEncoder.encode(request.password());
        User newUser = new User(request.userType(), request.name(), request.email(),
                request.login(), encodedPassword);

        return userGateway.save(newUser);
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
}