package com.example.shelter.controller;
import com.example.shelter.dto.AnimalDTO;
import com.example.shelter.dto.BoxDTO;
import com.example.shelter.entity.AnimalSpecies;
import com.example.shelter.mappers.AnimalMapper;
import com.example.shelter.repository.AnimalRepository;
import com.example.shelter.service.AnimalService;
import com.example.shelter.service.BoxService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
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

    @Autowired
    BoxService boxService;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    @Rollback
    @Transactional
    void testSaveNewAnimal() {
        AnimalDTO animalDTO = AnimalDTO.builder()
                .species(AnimalSpecies.valueOf("DOG"))
                .name("Rudy")
                .arrivalDate(LocalDate.now())
                .build();

        ResponseEntity<?> responseEntity = animalController.saveNewAnimal(animalDTO);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(201));
        assertThat(responseEntity.getHeaders().getLocation()).isNotNull();
        System.out.println(animalDTO.toString());
    }

    @Test
    @Rollback
    void testListAnimals() {
        AnimalDTO animalDTO = AnimalDTO.builder()
                .species(AnimalSpecies.valueOf("DOG"))
                .name("Rudy")
                .arrivalDate(LocalDate.now())
                .build();
        AnimalDTO animalDTO2 = AnimalDTO.builder()
                .species(AnimalSpecies.valueOf("DOG"))
                .name("Fala")
                .arrivalDate(LocalDate.now())
                .build();

        animalController.saveNewAnimal(animalDTO);
        animalController.saveNewAnimal(animalDTO2);
        List<AnimalDTO> dtos = animalController.listAnimals();
        assertThat(dtos.size()).isEqualTo(2);
    }

    @Rollback
    @Test
    void getAnimalById() throws Exception {
        AnimalDTO animalDTO = AnimalDTO.builder()
                .species(AnimalSpecies.DOG)
                .name("Rudy")
                .arrivalDate(LocalDate.now())
                .build();

        Integer id = animalController.saveNewAnimal(animalDTO).getBody().getId();
        AnimalDTO dto = animalController.getAnimalById(id);
        assertThat(dto.getId()).isEqualTo(id);
    }

    @Test
    void testGetAnimalByName() throws Exception {
        AnimalDTO animalDTO = AnimalDTO.builder()
                .species(AnimalSpecies.DOG)
                .name("Rudy")
                .arrivalDate(LocalDate.now())
                .build();

        animalController.saveNewAnimal(animalDTO);

        List<AnimalDTO> list = animalController.getAnimalByName("Rudy");

        //assertThat(list.get(0).getName()).isEqualTo("Rudy");
        Assertions.assertEquals("Rudy", list.get(0).getName());
        Assertions.assertEquals( 1, animalDTO.getBoxNumber());
    }

    @Test
    void testUpdatePatchById() throws Exception {
        AnimalDTO animalDTO = AnimalDTO.builder()
                .species(AnimalSpecies.DOG)
                .name("Rudy")
                .arrivalDate(LocalDate.now())
                .build();

        Integer id = animalController.saveNewAnimal(animalDTO).getBody().getId();

        AnimalDTO newDTO = AnimalDTO.builder()
                .name("Rudis")
                .build();
        ResponseEntity<?> responseEntity = animalController.updatePatchById(id, newDTO);


        mockMvc.perform(MockMvcRequestBuilders.get("/animals/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(newDTO.getName()))
                .andReturn();
    }

    @Test
    @Rollback
    @Transactional
    void testUpdateBox() {

        AnimalDTO animalDTO = AnimalDTO.builder()
                .species(AnimalSpecies.DOG)
                .name("Rudy")
                .arrivalDate(LocalDate.now())
                .build();

        AnimalDTO adto2 = animalController.saveNewAnimal(animalDTO).getBody();
        System.out.println(boxService.listBoxes().stream().map(BoxDTO::getBoxNumber).toList());
        System.out.println(animalDTO.getBoxNumber());
        assert adto2 != null;
        Assertions.assertEquals( 1, adto2.getBoxNumber());
    }
}