package com.example.shelter.mappers;

import com.example.shelter.dto.AdoptionDTO;
import com.example.shelter.entity.Adoption;
import org.mapstruct.Mapper;

@Mapper
public interface AdoptionMapper {
    Adoption toAdoption (AdoptionDTO adoptionDTO);
    AdoptionDTO toAdoptionDTO (Adoption adoption);
}
