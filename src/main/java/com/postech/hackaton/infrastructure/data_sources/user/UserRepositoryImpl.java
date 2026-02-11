package com.postech.hackaton.infrastructure.data_sources.user;

import com.postech.hackaton.dtos.transfer.user.UserDTO;
import com.postech.hackaton.dtos.transfer.user.NewUserDTO;
import com.postech.hackaton.infrastructure.mappers.UserEntityMapper;
import com.postech.hackaton.interface_adapter.data_sources.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserJpaRepository jpaRepository;

    @Override
    public Optional<UserDTO> findById(Long id) {
        return this.jpaRepository.findById(id)
                .map(UserEntityMapper::toUserDTO);
    }

    @Override
    public List<UserDTO> findAll(int size, long offset) {
        var pageRequest = PageRequest.of((int) offset / size, size);

        return this.jpaRepository.findAll(pageRequest)
                .stream()
                .map(UserEntityMapper::toUserDTO)
                .toList();
    }

    @Override
    public Optional<UserDTO> findByLogin(String login) {
        return this.jpaRepository.findByLogin(login)
                .map(UserEntityMapper::toUserDTO);
    }

    @Override
    public Optional<UserDTO> findByEmail(String email) {
        return this.jpaRepository.findByEmail(email)
                .map(UserEntityMapper::toUserDTO);
    }

    @Override
    public UserDTO save(NewUserDTO userDTO) {
        var entity = UserEntityMapper.toUserEntity(userDTO);

        var savedEntity = this.jpaRepository.save(entity);

        return UserEntityMapper.toUserDTO(savedEntity);
    }

    @Override
    public Optional<UserDTO> update(UserDTO userDTO, Long id) {
        var existingEntity = this.jpaRepository.existsById(id);

        if (!existingEntity) {
            return Optional.empty();
        }

        var entity = UserEntityMapper.toUserEntity(userDTO);
        var savedEntity = this.jpaRepository.save(entity);

        return Optional.of(UserEntityMapper.toUserDTO(savedEntity));
    }

    @Override
    public Integer delete(Long aLong) {
        this.jpaRepository.deleteById(aLong);
        return aLong.intValue();
    }
}
