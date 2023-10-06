package com.example.shelter.service;

import com.example.shelter.mappers.AnimalMapper;
import com.example.shelter.model.AnimalDTO;
import com.example.shelter.repository.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AnimalServiceImpl implements AnimalService {

    AnimalRepository animalRepository;
    AnimalMapper animalMapper;


    @Override
    public List<AnimalDTO> listAnimals() {
        return null;
    }

    @Override
    public Optional<AnimalDTO> getAnimalById(UUID id) {
        return Optional.empty();
    }

    @Override
    public AnimalDTO getAnimalByName(String name) {
        return null;
    }

    @Override
    public AnimalDTO createNewAnimal(AnimalDTO animal) {
        return null;
    }

    @Override
    public void deleteAnimal(AnimalDTO animal) {

    }
}
