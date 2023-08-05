package com.example.shelter.model;

import com.example.shelter.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DogService{

    @Autowired
    DogRepository repository;

    public Dog createNewDog(String name, int id) {
        Dog newOne = new Dog(name, id);
        repository.save(newOne);
        return newOne;
    }

    public List<Dog> getAll() {
        return repository.findAll();
    }

    public Dog getByName(String name) {
        System.out.println("try get by name from servce");
        Optional<Dog> dog = repository.findById(name);
        if (dog.isPresent()) {
            return dog.get();
        }
        return new Dog();
    }

    public Dog getById(String id) {
        Optional<Dog> dog = repository.findById(id);
        if (dog.isPresent()) {
            return dog.get();
        }
        return new Dog();
    }

    public void remove(String id) {
        /*Optional<Dog> dog = repository.findById(name);
         if (dog.isPresent()) {
             repository.delete(dog.get());
         }*/
        repository.deleteById(id);

    }

    public void remove(Dog dog) {
        repository.delete(dog);

    }


}
