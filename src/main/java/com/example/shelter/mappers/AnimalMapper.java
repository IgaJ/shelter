package com.example.shelter.mappers;

import com.example.shelter.entity.Animal;
import com.example.shelter.dto.AnimalDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper
public interface AnimalMapper {
    Animal animalDTOToAnimal (AnimalDTO dto);

    @Mapping(target = "boxNumber", source = "box.number") // @mapping definiuje niestandardowe mapowanie, target to pole w DTO a source to pole w box
    AnimalDTO animalToAnimalDTO (Animal animal);

}
