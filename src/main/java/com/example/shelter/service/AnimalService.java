package com.example.shelter.service;

import com.example.shelter.model.AnimalDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AnimalService {

    AnimalDTO saveNewAnimal(AnimalDTO animal);

    List<AnimalDTO> listAnimals();

    Optional<AnimalDTO> getAnimalById(UUID id);

    List<AnimalDTO> getAnimalByName(String name);

    void deleteById(UUID id);

    List<AnimalDTO> getByAge(Integer age);

    List<AnimalDTO> getVaccinated(String vaccinated);
}
