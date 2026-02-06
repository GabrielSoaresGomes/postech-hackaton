package com.postech.hackaton.interface_adapter.gateways;

import com.postech.hackaton.application.gateways.MedicalCareGateway;
import com.postech.hackaton.application.mappers.MedicalCareMapper;
import com.postech.hackaton.domain.MedicalCare;
import com.postech.hackaton.exceptions.ResourceNotFoundException;
import com.postech.hackaton.interface_adapter.data_sources.repositories.MedicalCareRepository;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class MedicalCareGatewayImpl implements MedicalCareGateway {
    private final MedicalCareRepository repository;

    public MedicalCareGatewayImpl(MedicalCareRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<MedicalCare> findAll(Pageable pageable) {
        return this.repository.findAll(pageable.getPageSize(), pageable.getOffset())
                .stream()
                .map(MedicalCareMapper::toMedicalCare)
                .toList();
    }

    @Override
    public MedicalCare findById(Long id) {
        return this.repository.findById(id)
                .map(MedicalCareMapper::toMedicalCare)
                .orElseThrow(() -> new ResourceNotFoundException("Atendimento não encontrado para o id %d".formatted(id)));
    }

    @Override
    public MedicalCare save(MedicalCare medicalCare) {
        var newMedicalCareDTO = MedicalCareMapper.toNewMedicalCareDTO(medicalCare);
        var savedMedicalCareDTO = this.repository.save(newMedicalCareDTO);

        return MedicalCareMapper.toMedicalCare(savedMedicalCareDTO);
    }

    @Override
    public MedicalCare update(MedicalCare entity, Long aLong) {
        // TODO implementar
        return null;
    }

    @Override
    public void delete(Long aLong) {
        // TODO implementar
    }
}
