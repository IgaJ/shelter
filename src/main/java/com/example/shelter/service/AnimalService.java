package com.example.shelter.service;

import com.example.shelter.model.AnimalDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AnimalService {

    AnimalDTO saveNewAnimal(AnimalDTO animal);

    List<AnimalDTO> listAnimals();

    Optional<AnimalDTO> getAnimalById(UUID id);

    AnimalDTO getAnimalByName(String name);

    void deleteAnimal(AnimalDTO animal);
}
