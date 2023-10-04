package com.example.shelter.service;

import com.example.shelter.model.Animal;
import com.example.shelter.model.Cat;
import com.example.shelter.model.Dog;

import java.util.List;
import java.util.UUID;

public interface AnimalService {

    Animal createNewAnimal();

    List<Animal> listAnimals();

    Animal getAnimalById();

    Animal getAnimalByName(String name);

    void deleteAnimalById(UUID id);

    void deleteAnimal(Animal animal);
}
