package com.postech.hackaton.interface_adapter.gateways;

import com.postech.hackaton.domain.User;
import com.postech.hackaton.application.gateways.UserGateway;
import com.postech.hackaton.interface_adapter.data_sources.repositories.UserRepository;
import com.postech.hackaton.application.mappers.UserMapper;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public class UserGatewayImpl implements UserGateway {
    private final UserRepository userRepository;

    public UserGatewayImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        var newUserDTO = UserMapper.toNewUserDTO(user);
        var savedEntity = userRepository.save(newUserDTO);
        return UserMapper.toUser(savedEntity);
    }

    @Override
    public List<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable.getPageSize(), pageable.getOffset())
                .stream()
                .map(UserMapper::toUser)
                .toList();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .map(UserMapper::toUser)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return userRepository.findByLogin(login).map(UserMapper::toUser);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email).map(UserMapper::toUser);
    }

    @Override
    public Integer delete(Long id) {
        return userRepository.delete(id);
    }

    @Override
    public User update(User entity, Long id) {
        //TODO implementar
        return null;
    }
}