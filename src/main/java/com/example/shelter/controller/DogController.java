package com.example.shelter.controller;

import com.example.shelter.model.Dog;
import com.example.shelter.model.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
public class DogController {
    @Autowired
    DogService service;

    @GetMapping("/dogs")
    public List<Dog> getAllDogs(){
        return service.getAll();
    }

    /*@GetMapping("dogs/{id}")
    public Dog getById(@PathVariable String id) {
        return service.getById(id);
    }*/

    @GetMapping("/dogs/{name}")
    public Dog getByName(@PathVariable String name) {
        System.out.println("try get by name from controller");
        return service.getByName(name);
    }

    // Post /api/dogs - stwórz psa
    @PostMapping("/dogs")
    public void createDog (@RequestBody Dog dog) {
        service.createNewDog(dog.getName(), dog.getId());
    }

    // Delete /api/dogs/name - usuń psa o imieniu name
    @DeleteMapping ("/dogs")
    public void deleteDog(@RequestBody Dog dog) { // w postmanie oczekuje na body czyli podać kod do wykonania
        service.remove(dog);
    }

    @DeleteMapping ("/dogs/{id}")
    public void deleteDog2(@PathVariable String id) {
        service.remove(id);
    }


    //[{"id":1,"name":"rudy"},{"id":2,"name":"salatka"}, {"id":3,"name":"puris"}
    //
    // {"name":"dino","id":2},{"name":"salatka","id":3},{"name":"sernik","id":4}]

}
