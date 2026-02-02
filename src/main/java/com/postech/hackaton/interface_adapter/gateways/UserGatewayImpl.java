package com.postech.hackaton.interface_adapter.gateways;

import com.postech.hackaton.domain.User;
import com.postech.hackaton.application.gateways.UserGateway;
import com.postech.hackaton.infrastructure.data_sources.UserRepository;
import com.postech.hackaton.infrastructure.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserGatewayImpl implements UserGateway {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User create(User user) {
        var entity = userMapper.toEntity(user);
        var savedEntity = userRepository.save(entity);
        return userMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id).map(userMapper::toDomain);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return userRepository.findByLogin(login).map(userMapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email).map(userMapper::toDomain);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}