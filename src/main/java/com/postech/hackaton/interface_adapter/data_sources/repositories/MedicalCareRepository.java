package com.postech.hackaton.interface_adapter.data_sources.repositories;

import com.postech.hackaton.dtos.transfer.medical_care.MedicalCareDTO;
import com.postech.hackaton.dtos.transfer.medical_care.NewMedicalCareDTO;

public interface MedicalCareRepository extends CrudRepository<NewMedicalCareDTO, MedicalCareDTO, Long>{
}
