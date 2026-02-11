package com.postech.hackaton.interface_adapter.data_sources.repositories;

import com.postech.hackaton.dtos.transfer.user.NewUserDTO;
import com.postech.hackaton.dtos.transfer.user.UserDTO;

import java.util.Optional;

public interface UserRepository extends CrudRepository<NewUserDTO, UserDTO, Long> {
    Optional<UserDTO> findByLogin(String login);
    Optional<UserDTO> findByEmail(String email);
}
