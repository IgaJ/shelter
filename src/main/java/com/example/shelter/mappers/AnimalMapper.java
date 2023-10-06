package com.example.shelter.mappers;

import com.example.shelter.entities.Animal;
import com.example.shelter.model.AnimalDTO;
import org.mapstruct.Mapper;

@Mapper
public interface AnimalMapper {
    Animal animalDTOToAnimal (AnimalDTO dto);
    AnimalDTO animalToAnimalDTO (Animal animal);
}
