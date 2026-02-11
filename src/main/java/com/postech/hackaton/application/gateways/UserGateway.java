package com.postech.hackaton.application.gateways;

import com.postech.hackaton.domain.User;
import java.util.Optional;

public interface UserGateway extends CrudGateway<User, Long>{
    Optional<User> findByLogin(String login);
    Optional<User> findByEmail(String email);
}