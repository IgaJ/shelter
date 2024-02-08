package com.example.shelter.service;

import com.example.shelter.dto.AnimalDTO;
import com.example.shelter.dto.BoxDTO;

import java.util.List;
import java.util.Optional;

public interface AnimalService {

    AnimalDTO saveNewAnimal(AnimalDTO animal);

    List<AnimalDTO> listAnimals();

    List<AnimalDTO> getAnimalByName(String name);

    List<AnimalDTO> getByAge(Integer age);

    List<AnimalDTO> getBySex(String sex);

    List<AnimalDTO> getAnimalsBySize(String size);

    Optional<AnimalDTO> getAnimalById(Integer id);

    void deleteById(Integer id);

    Optional<AnimalDTO> patchAnimalById(Integer animalId, AnimalDTO animalDTO);

   AnimalDTO vaccinate(Integer id);

    List<AnimalDTO> listNonVaccinated();

    List<AnimalDTO> listAvailableForAdoption();

    AnimalDTO changeBoxToGivenBoxNumber(Integer animalId, BoxDTO boxDTO);

    public AnimalDTO changeBoxToAnyBoxNumberWithNoQuarantineStatus(Integer animalId);

    public AnimalDTO changeBoxToAnyBoxNumberWithYesQuarantineStatus(Integer animalId);
}

