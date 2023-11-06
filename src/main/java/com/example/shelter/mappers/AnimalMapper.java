package com.example.shelter.mappers;

import com.example.shelter.entity.Animal;
import com.example.shelter.dto.AnimalDTO;
import org.mapstruct.Mapper;

@Mapper
public interface AnimalMapper {
    Animal animalDTOToAnimal (AnimalDTO dto);
    AnimalDTO animalToAnimalDTO (Animal animal);

}
