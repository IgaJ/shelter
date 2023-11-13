package com.example.shelter.controller;

import com.example.shelter.dto.AnimalDTO;
import com.example.shelter.entity.Animal;
import com.example.shelter.entity.AnimalSpecies;
import com.example.shelter.mappers.AnimalMapper;
import com.example.shelter.repository.AnimalRepository;
import com.example.shelter.service.AnimalService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AnimalControllerTest {
    @Autowired
    AnimalController animalController;

    @Autowired
    AnimalRepository animalRepository;

    @Autowired
    AnimalService animalService;

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
                .name("Rudy")
                .arrivalDate(Date.valueOf("2022-10-11"))
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

/*    @Rollback
    @Transactional
    @Test
    void updatePatchById() {


        AnimalDTO animalDTO = AnimalDTO.builder()
                .species(AnimalSpecies.valueOf("DOG"))
                .name("Rudy")
                .arrivalDate(Date.valueOf("2022-10-11"))
                .build();

        AnimalDTO saved = animalService.saveNewAnimal(animalDTO);

        String updatedName = "updated";
        saved.setName(updatedName);

        saved = (animalMapper.animalToAnimalDTO(animalRepository.findAll().get(0)));

        ResponseEntity<?> responseEntity = animalController.updatePatchById(saved.getId(), animalDTO);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        Animal updated = animalRepository.findAll().get(0);
        assertThat(updated.getName()).isEqualTo(updatedName);
        assertThat(updated.getAdopted()).isNotNull();
        System.out.println(animalDTO);
    }*/
}