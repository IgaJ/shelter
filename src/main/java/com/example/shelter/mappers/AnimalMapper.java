package com.example.shelter.mappers;

import com.example.shelter.entities.Animal;
import com.example.shelter.model.AnimalDTO;

public interface AnimalMapper {
    Animal animalDTOToAnimal (AnimalDTO dto);
    AnimalDTO animalToAnimalDTO (Animal animal);
}
