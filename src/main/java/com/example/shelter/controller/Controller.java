package com.example.shelter.controller;

import com.example.shelter.model.Cat;
import com.example.shelter.model.Dog;
import com.example.shelter.model.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;


//TODO get by name i get by id za pomocą query parameters
// https://www.baeldung.com/spring-request-param
// https://www.semrush.com/blog/url-parameters/

@RestController
public class Controller {
    @Autowired
    Service service;

    @GetMapping("/dogs")
    public List<Dog> getAllDogs(){
        return service.getAllDogs();
    }

    @GetMapping("/cats")
    public List<Cat> getAllCats() {
        return service.getAllCats();
    }

    @GetMapping("/dogs/name/{name}")
    //@GetMapping("/dogs/name/{name}")
    public List<Dog> getDogByName(@PathVariable String name) {
        return service.getDogByName(name);
    }
    @GetMapping("/dogs/id/{id}")
    public Dog getDogById(@PathVariable int id) {
        return service.getDogById(id);
    }

    // @RequestParam w endpoincie możemy posługiwać sie skłądnią url: /dogs/name?name=rudy
    @GetMapping ("/dogs/name")
    public List<Dog> getDogsByName(@RequestParam String name) {
        return service.getDogByName(name);
    }
    @GetMapping("/dogs/id")
    public Dog getDogsById(@RequestParam int id) {
        return service.getDogById(id);
    }

    @GetMapping ("/cats/name") // /cats/name?name=felek
    public List<Cat> getCatsByName(@RequestParam String name) {
        return service.getCatByName(name);
    }
    @GetMapping("/cats/id") // cats/id?id=1
    public Cat getCatsById(@RequestParam int id) {
        return service.getCatById(id);
    }

    // Post /api/dogs - stwórz psa
    @PostMapping("/dogs")
    public void createDog (@RequestBody Dog dog) {
        service.createNewDog(dog.getId(), dog.getName());
    }

    @PostMapping ("/cats")
    public void createCat (@RequestBody Cat cat) {
        service.createNewCat(cat.getId(), cat.getName());
    }


    // Delete /api/dogs/name - usuń psa z request body
    // w postmanie oczekuje na body czyli podać kod do wykonania
    @DeleteMapping ("/dogs")
    public void deleteDog(@RequestBody Dog dog) {
        service.removeDog(dog);
    }
    @DeleteMapping ("/cats")
    public void deleteCat(@RequestBody Cat cat) {
        service.removeCat(cat);
    }

    @DeleteMapping ("/dogs/{id}")
    public void deleteDogById(@PathVariable int id) {
        service.removeDogById(id);
    }
    @DeleteMapping("/cats/{id}")
    public void deleteCatById(@PathVariable int id) {
        service.removeCatById(id);
    }
}

//[{"id":1,"name":"rudy"},{"id":2,"name":"dino"}, {"id":3,"name":"puris"}
// [{"id":1,"name":"prazek"},{"id":2,"name":"felek"}, {"id":3,"name":"kocisk"},