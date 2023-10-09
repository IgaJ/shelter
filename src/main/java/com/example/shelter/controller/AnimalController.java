package com.example.shelter.controller;

import com.example.shelter.model.AnimalDTO;
import com.example.shelter.service.AnimalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/animals")
public class AnimalController {

    public static final String CAT_PATH_ID = "/cats/id/{id}";
    public static final String DOG_PATH_ID = "/dogs/id/{id}";

    private final AnimalService animalService;

    @PostMapping
    public ResponseEntity saveNewAnimal (@RequestBody AnimalDTO animalDTO) {
        AnimalDTO savedAnimal = animalService.saveNewAnimal(animalDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/animals/id/" + savedAnimal.getId().toString());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @GetMapping
    public List<AnimalDTO> listAnimals() {
        return animalService.listAnimals();
    }

    @GetMapping("/id/{id}")
    public AnimalDTO getAnimalById(@PathVariable ("id") UUID id) {
        return animalService.getAnimalById(id).orElseThrow(null);
    }

    @GetMapping("/name/{name}")
    public AnimalDTO getAnimalByName(@PathVariable ("name") String name) {
        return null;
    }


}


