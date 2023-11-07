package com.example.shelter.controller;

import com.example.shelter.dto.AnimalDTO;
import com.example.shelter.entity.AnimalSpecies;
import com.example.shelter.mappers.AnimalMapper;
import com.example.shelter.repository.AnimalRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AnimalControllerTest {
    @Autowired
    AnimalController animalController;

    @Autowired
    AnimalRepository animalRepository;

    @Autowired
    AnimalMapper animalMapper;

    @Autowired
    WebApplicationContext wac;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    @Rollback
    @Transactional
    void saveNewAnimal() {
        AnimalDTO animalDTO = AnimalDTO.builder()
                .species(AnimalSpecies.valueOf("DOG"))
                .vaccinated("yes")
                .name("Rudy")
                .arrival("10-2022")
                .build();

        ResponseEntity<?> responseEntity = animalController.saveNewAnimal(animalDTO);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(201));
        assertThat(responseEntity.getHeaders().getLocation()).isNotNull();
        System.out.println(animalDTO.toString());



    }

    @Test
    void listAnimals() {
        List<AnimalDTO> dtos = animalController.listAnimals();
        System.out.println(dtos.size());
    }

    @Test
    void getAnimalById() {
    }

    @Test
    void getAnimalByName() {
    }

    @Test
    void testSaveNewAnimal() {
    }

    @Test
    void testListAnimals() {
    }
}