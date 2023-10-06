package com.example.shelter.controller;

import com.example.shelter.model.AnimalDTO;
import com.example.shelter.service.AnimalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/animals")
public class AnimalController {

    public static final String CAT_PATH_ID = "/cats/id/id}";
    public static final String DOG_PATH_ID = "/dogs/id/{id}";

    private final AnimalService animalService;

    @GetMapping("/animals")
    public List<AnimalDTO> listAnimals() {
        return animalService.listAnimals();
    }





}
