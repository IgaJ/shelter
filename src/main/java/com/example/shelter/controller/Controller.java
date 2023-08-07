package com.example.shelter.controller;

import com.example.shelter.model.Cat;
import com.example.shelter.model.Dog;
import com.example.shelter.model.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;


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

    /*@GetMapping("dogs/{id}")
    public Dog getById(@PathVariable String id) {
        return service.getById(id);
    }*/

    @GetMapping("/dogs/{name}")
    public Dog getDogByName(@PathVariable String name) {
        System.out.println("try get by name from controller");
        return service.getDogByName(name);
    }

    @GetMapping ("/cats/{name}")
    public Cat getCatByName (@PathVariable String name) {
        return service.getCatByName(name);
    }

    // Post /api/dogs - stwórz psa
    @PostMapping("/dogs")
    public void createDog (@RequestBody Dog dog) {
        service.createNewDog(dog.getName(), dog.getId());
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
    public void deleteDog2(@PathVariable String id) {
        service.removeDog(id);
    }
    @DeleteMapping("/cats/{id}")
    public void delketeCat2(@PathVariable String id) {
        service.removeCat(id);
    }




    //[{"id":1,"name":"rudy"},{"id":2,"name":"dino"}, {"id":3,"name":"puris"}
    // [{"id":1,"name":"prazek"},{"id":2,"name":"felek"}, {"id":3,"name":"kocisk"}
    //
    // {"name":"dino","id":2},{"name":"salatka","id":3},{"name":"sernik","id":4}]

}
