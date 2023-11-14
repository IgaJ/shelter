package com.example.shelter.mappers;

import com.example.shelter.dto.VaccinationDTO;
import com.example.shelter.entity.Vaccination;
import org.mapstruct.Mapper;

@Mapper
public interface VaccinationMapper {
    Vaccination toVaccination (VaccinationDTO vaccinationDTO);
    VaccinationDTO toVaccinationDTO (Vaccination vaccination);
}
