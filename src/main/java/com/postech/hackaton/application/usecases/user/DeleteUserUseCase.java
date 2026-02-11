package com.postech.hackaton.application.usecases.user;

import com.postech.hackaton.application.gateways.UserGateway;
import com.postech.hackaton.application.usecases.UseCase;
import com.postech.hackaton.domain.User;

public class DeleteUserUseCase implements UseCase<Long, Integer> {
    private final UserGateway userGateway;
    private final FindUserByIdUseCase findUserByIdUseCase;

    public DeleteUserUseCase(UserGateway userGateway, FindUserByIdUseCase findUserByIdUseCase) {
        this.userGateway = userGateway;
        this.findUserByIdUseCase = findUserByIdUseCase;
    }

    @Override
    public Integer execute(Long id) {
        User user = this.findUserByIdUseCase.execute(id);
        if (user == null) {
            throw new RuntimeException("Usuário não encontrado");
        }

        return this.userGateway.delete(id);
    }

}
