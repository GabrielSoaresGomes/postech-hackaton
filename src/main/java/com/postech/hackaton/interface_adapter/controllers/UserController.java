package com.postech.hackaton.interface_adapter.controllers;

import com.postech.hackaton.application.gateways.UserGateway;
import com.postech.hackaton.application.usecases.user.CreateUserUseCase;
import com.postech.hackaton.application.usecases.user.DeleteUserUseCase;
import com.postech.hackaton.application.usecases.user.FindUserByIdUseCase;
import com.postech.hackaton.application.usecases.user.ListUsersUseCase;
import com.postech.hackaton.dtos.requests.user.CreateUserRequestDTO;
import com.postech.hackaton.dtos.requests.user.ListUserRequestDTO;
import com.postech.hackaton.dtos.responses.UserResponse;
import com.postech.hackaton.infrastructure.mappers.UserEntityMapper;
import com.postech.hackaton.interface_adapter.data_sources.repositories.UserRepository;
import com.postech.hackaton.interface_adapter.gateways.UserGatewayImpl;
import com.postech.hackaton.interface_adapter.presenters.UserPresenter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

public class UserController {
    private final CreateUserUseCase createUserUseCase;
    private final ListUsersUseCase listUsersUseCase;
    private final FindUserByIdUseCase findUserByIdUseCase;
    private final DeleteUserUseCase deleteUserUseCase;

    public UserController(
            UserRepository repository
    ) {
        var userGateway = new UserGatewayImpl(repository);

        this.createUserUseCase = new CreateUserUseCase(userGateway, new BCryptPasswordEncoder());
        this.listUsersUseCase = new ListUsersUseCase(userGateway);
        this.findUserByIdUseCase = new FindUserByIdUseCase(userGateway);
        this.deleteUserUseCase = new DeleteUserUseCase(userGateway, this.findUserByIdUseCase);
    }

    public List<UserResponse> list(ListUserRequestDTO request) {
        var users = this.listUsersUseCase.execute(request);

        return UserPresenter.userListToUserResponseDTOList(users);
    }

    public UserResponse find(Long id) {
        var user = this.findUserByIdUseCase.execute(id);

        return UserPresenter.userToUserResponse(user);
    }

    public UserResponse create(CreateUserRequestDTO request) {
        var createdUser = this.createUserUseCase.execute(request);
        return UserPresenter.userToUserResponse(createdUser);
    }

    public Integer delete(Long id) {
        return this.deleteUserUseCase.execute(id);
    }
}
