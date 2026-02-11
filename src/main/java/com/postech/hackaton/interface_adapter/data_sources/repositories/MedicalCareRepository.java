package com.postech.hackaton.interface_adapter.data_sources.repositories;

import com.postech.hackaton.dtos.transfer.medical_care.MedicalCareDTO;
import com.postech.hackaton.dtos.transfer.medical_care.NewMedicalCareDTO;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface MedicalCareRepository extends CrudRepository<NewMedicalCareDTO, MedicalCareDTO, Long>{
    List<MedicalCareDTO> findAll(int size, long offset, Sort sort);
    List<MedicalCareDTO> findAll(int size, long offset, Sort sort, Boolean priorityAccess);
}
