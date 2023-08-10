package com.example.shelter.model;

import com.example.shelter.repository.CatRepository;
import com.example.shelter.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    DogRepository dogRepository;
    @Autowired
    CatRepository catRepository;

    public Dog createNewDog(int id, String name) {
        Dog newOne = new Dog(id, name);
        dogRepository.save(newOne);
        return newOne;
    }

    public Cat createNewCat (int id, String name) {
        Cat newOne = new Cat(id, name);
        catRepository.save(newOne);
        return newOne;
    }

    public List<Dog> getAllDogs() {
        return dogRepository.findAll();
    }

    public List<Cat> getAllCats() {
        return catRepository.findAll();
    }

    public List<Dog> getDogByName(String name) {
        return dogRepository.getDogByName(name);
    }

    public List<Cat> getCatByName(String name) {
        return catRepository.getCatByName(name);
    }

    public Dog getDogById(int id) {
        Optional<Dog> dog = dogRepository.findById(id);
        dogRepository.findById(1);
        if (dog.isPresent()) {
            return dog.get();
        }
        return new Dog();
    }

    public Cat getCatById(int id) {
        Optional<Cat> cat = catRepository.findById(String.valueOf(id));
        if (cat.isPresent()) {
            return  cat.get();
        }
        return new Cat();
    }


    public void removeDogById(int id) {
        Optional<Dog> dog = dogRepository.findById(id);
         if (dog.isPresent()) {
             dogRepository.delete(dog.get());
         }
    }
    public void removeCatById(int id) {
        catRepository.deleteById(String.valueOf(id));
    }
    public void removeDog(Dog dog) {
        dogRepository.delete(dog);
    }

    public void removeCat(Cat cat) {
        catRepository.delete(cat);
    }
}
