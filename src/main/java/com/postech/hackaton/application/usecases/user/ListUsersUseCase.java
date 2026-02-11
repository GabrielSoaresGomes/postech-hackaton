package com.postech.hackaton.application.usecases.user;

import com.postech.hackaton.application.gateways.MedicalCareGateway;
import com.postech.hackaton.application.gateways.UserGateway;
import com.postech.hackaton.application.usecases.UseCase;
import com.postech.hackaton.domain.MedicalCare;
import com.postech.hackaton.domain.User;
import com.postech.hackaton.dtos.requests.medical_care.ListMedicalCareRequestDTO;
import com.postech.hackaton.dtos.requests.user.ListUserRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@Slf4j
public class ListUsersUseCase implements UseCase<ListUserRequestDTO, List<User>> {
    private final UserGateway userGateway;

    public ListUsersUseCase(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public List<User> execute(ListUserRequestDTO request) {
        log.info("Listando atendimentos");
        return this.userGateway.findAll(PageRequest.of(request.page(), request.size()));
    }
}

