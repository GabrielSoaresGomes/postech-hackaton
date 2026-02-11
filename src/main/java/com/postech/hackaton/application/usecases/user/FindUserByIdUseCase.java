package com.postech.hackaton.application.usecases.user;

import com.postech.hackaton.application.gateways.UserGateway;
import com.postech.hackaton.application.usecases.UseCase;
import com.postech.hackaton.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
public class FindUserByIdUseCase implements UseCase<Long, User> {
    private final UserGateway userGateway;

    public FindUserByIdUseCase(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public User execute(Long id) {
        log.info("Buscando atendimento com id {}", id);
        var user = this.userGateway.findById(id);

        log.info("Usuário encontrado {}", user);
        return user;
    }
}