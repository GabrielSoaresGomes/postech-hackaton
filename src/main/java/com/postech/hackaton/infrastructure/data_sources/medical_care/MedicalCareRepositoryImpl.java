package com.postech.hackaton.infrastructure.data_sources.medical_care;

import com.postech.hackaton.dtos.transfer.medical_care.MedicalCareDTO;
import com.postech.hackaton.dtos.transfer.medical_care.NewMedicalCareDTO;
import com.postech.hackaton.infrastructure.mappers.MedicalCareEntityMapper;
import com.postech.hackaton.interface_adapter.data_sources.repositories.MedicalCareRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MedicalCareRepositoryImpl implements MedicalCareRepository {
    private final MedicalCareJpaRepository jpaRepository;

    @Override
    public Optional<MedicalCareDTO> findById(Long id) {
        return this.jpaRepository.findById(id)
                .map(MedicalCareEntityMapper::toMedicalCareDTO);
    }

    @Override
    public List<MedicalCareDTO> findAll(int size, long offset) {
        var pageRequest = PageRequest.of((int) offset / size, size);

        return this.jpaRepository.findAll(pageRequest)
                .stream()
                .map(MedicalCareEntityMapper::toMedicalCareDTO)
                .toList();
    }

    @Override
    public MedicalCareDTO save(NewMedicalCareDTO medicalCareDTO) {
        var entity = MedicalCareEntityMapper.toMedicalCareEntity(medicalCareDTO);

        var savedEntity = this.jpaRepository.save(entity);

        return MedicalCareEntityMapper.toMedicalCareDTO(savedEntity);
    }

    @Override
    public Optional<MedicalCareDTO> update(MedicalCareDTO medicalCareDTO, Long id) {
        var existingEntity = this.jpaRepository.existsById(id);

        if (!existingEntity) {
            return Optional.empty();
        }

        var entity = MedicalCareEntityMapper.toMedicalCareEntity(medicalCareDTO);
        var savedEntity = this.jpaRepository.save(entity);

        return Optional.of(MedicalCareEntityMapper.toMedicalCareDTO(savedEntity));
    }

    @Override
    public Integer delete(Long aLong) {
        // TODO implementar
        return 0;
    }
}
