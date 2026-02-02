package com.postech.hackaton.application.usecases;

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
}