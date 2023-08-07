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

    public Dog createNewDog(String name, int id) {
        Dog newOne = new Dog(name, id);
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

    public Dog getDogByName(String name) {
        System.out.println("try get by name from servce");
        Optional<Dog> dog = dogRepository.findById(name);
        if (dog.isPresent()) {
            return dog.get();
        }
        return new Dog();
    }

    public Cat getCatByName(String name) {
        Optional<Cat> cat = catRepository.findById(name);
        if (cat.isPresent()) {
            return cat.get();
        }
        return new Cat();
    }


    public Dog getById(String id) {
        Optional<Dog> dog = dogRepository.findById(id);
        if (dog.isPresent()) {
            return dog.get();
        }
        return new Dog();
    }

    public Cat getCatById(String id) {
        Optional<Cat> cat = catRepository.findById(id);
        if (cat.isPresent()) {
            return  cat.get();
        }
        return new Cat();
    }

    public void removeDog(String id) {
        /*Optional<Dog> dog = repository.findById(name);
         if (dog.isPresent()) {
             repository.delete(dog.get());
         }*/
        dogRepository.deleteById(id);

    }
    public void removeCat(String id) {
        catRepository.deleteById(id);
    }
    public void removeDog(Dog dog) {
        dogRepository.delete(dog);

    }
    public void removeCat(Cat cat) {
        catRepository.delete(cat);
    }
}
