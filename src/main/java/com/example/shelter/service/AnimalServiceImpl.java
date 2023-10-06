package com.example.shelter.service;

import com.example.shelter.mappers.AnimalMapper;
import com.example.shelter.model.AnimalDTO;
import com.example.shelter.repository.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;
    private final AnimalMapper animalMapper;

    @Override
    public AnimalDTO saveNewAnimal(AnimalDTO animal) {
        return animalMapper.animalToAnimalDTO(animalRepository.save(animalMapper.animalDTOToAnimal(animal)));
    }

    @Override
    public List<AnimalDTO> listAnimals() {
        return animalRepository.findAll()
                .stream()
                .map(animal -> animalMapper.animalToAnimalDTO(animal))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AnimalDTO> getAnimalById(UUID id) {
        return Optional.ofNullable(animalMapper.animalToAnimalDTO(animalRepository.findById(id).orElse(null)));
    }

    @Override
    public AnimalDTO getAnimalByName(String name) {
        return null;
    }

    @Override
    public void deleteAnimal(AnimalDTO animal) {
    }
}
