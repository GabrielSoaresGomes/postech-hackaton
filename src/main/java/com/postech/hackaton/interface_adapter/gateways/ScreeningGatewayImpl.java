package com.postech.hackaton.interface_adapter.gateways;

import com.postech.hackaton.domain.Screening;
import com.postech.hackaton.application.gateways.ScreeningGateway;
import com.postech.hackaton.infrastructure.data_sources.ScreeningRepository;
import com.postech.hackaton.infrastructure.mappers.ScreeningMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ScreeningGatewayImpl implements ScreeningGateway {
    private final ScreeningRepository screeningRepository;
    private final ScreeningMapper screeningMapper;

    @Override
    public Screening create(Screening screening) {
        var entity = screeningMapper.toEntity(screening);
        var savedEntity = screeningRepository.save(entity);
        return screeningMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Screening> findById(Long id) {
        return screeningRepository.findById(id).map(screeningMapper::toDomain);
    }

    @Override
    public List<Screening> findByPatientId(Long patientId) {
        return screeningRepository.findByPatientId(patientId).stream()
                .map(screeningMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Screening> findByHealthProfessionalId(Long healthProfessionalId) {
        return screeningRepository.findByHealthProfessionalId(healthProfessionalId).stream()
                .map(screeningMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Screening> findByCompleted(Boolean completed) {
        return screeningRepository.findByCompleted(completed).stream()
                .map(screeningMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public Screening update(Screening screening) {
        var entity = screeningMapper.toEntity(screening);
        var updatedEntity = screeningRepository.save(entity);
        return screeningMapper.toDomain(updatedEntity);
    }

    @Override
    public void delete(Long id) {
        screeningRepository.deleteById(id);
    }
}