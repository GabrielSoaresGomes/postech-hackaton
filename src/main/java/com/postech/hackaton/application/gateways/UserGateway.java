package com.postech.hackaton.application.gateways;

import com.postech.hackaton.domain.User;
import java.util.Optional;

public interface UserGateway {
    User create(User user);
    Optional<User> findById(Long id);
    Optional<User> findByLogin(String login);
    Optional<User> findByEmail(String email);
    void delete(Long id);
}