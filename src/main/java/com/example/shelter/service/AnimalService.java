package com.example.shelter.service;

import com.example.shelter.dto.AnimalDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AnimalService {

    AnimalDTO saveNewAnimal(AnimalDTO animal);

    List<AnimalDTO> listAnimals();

    List<AnimalDTO> getAnimalByName(String name);

    List<AnimalDTO> getByAge(Integer age);

    List<AnimalDTO> getBySex(String sex);

    List<AnimalDTO> getAnimalsBySize(String size);

    Optional<AnimalDTO> getAnimalById(UUID id);

    void deleteById(UUID id);

    Optional<AnimalDTO> patchAnimalById(UUID animalId, AnimalDTO animalDTO);

    AnimalDTO vaccinate(UUID id);

    List<AnimalDTO> listNonVaccinated();

    List<AnimalDTO> listAvailableForAdoption();
}

