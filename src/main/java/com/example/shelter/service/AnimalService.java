package com.example.shelter.service;

import com.example.shelter.dto.AnimalDTO;

import java.util.List;
import java.util.Optional;

public interface AnimalService {

    AnimalDTO saveNewAnimal(AnimalDTO animal);

    void vaccinate(Integer id);
    void walk(Integer id);
    void adopt(Integer id);

    List<AnimalDTO> listAnimals();

    List<AnimalDTO> getAnimalByName(String name);

    List<AnimalDTO> getByAge(Integer age);

    List<AnimalDTO> getBySex(String sex);

    List<AnimalDTO> getAnimalsBySize(String size);

    Optional<AnimalDTO> getAnimalById(Integer id);

    void deleteById(Integer id);

    Optional<AnimalDTO> patchAnimalById(Integer animalId, AnimalDTO animalDTO);

    List<AnimalDTO> listNonVaccinated();

    List<AnimalDTO> listAvailableForAdoption();
}

