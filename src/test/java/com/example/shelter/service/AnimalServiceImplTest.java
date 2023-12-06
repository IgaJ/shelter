package com.example.shelter.service;

import com.example.shelter.entity.Box;
import com.example.shelter.mappers.AnimalMapper;
import com.example.shelter.mappers.AnimalMapperImpl;
import com.example.shelter.repository.AnimalRepository;
import com.example.shelter.repository.BoxRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class AnimalServiceImplTest {


    private int maxAnimalsInBox = 4;

    @Mock
    AnimalRepository animalRepository;
    AnimalMapper animalMapper = new AnimalMapperImpl();
    BoxService boxService = Mockito.mock(BoxService.class);
    BoxRepository boxRepository = Mockito.mock(BoxRepository.class);
    AnimalServiceImpl animalService;

    @BeforeEach
    void init() {
        animalService = new AnimalServiceImpl(animalRepository, animalMapper, boxService, boxRepository);
    }


    @Test
    void test2() {
        List<Box> boxes = new ArrayList<>(List.of(new Box(), new Box()));
        ArgumentCaptor<Boolean> captor = ArgumentCaptor.forClass(Boolean.class);
        ArgumentCaptor<Integer> captor1 = ArgumentCaptor.forClass(Integer.class);
        Mockito.when(boxRepository
                .findBoxesWithSizeLessThanAndQuarantine(captor1.capture(), captor.capture()))
                .thenReturn(boxes);

        Assertions.assertNotNull(animalService.findAvailableBox());
        Assertions.assertEquals(true, captor.getValue());
        Assertions.assertEquals(4, captor1.getValue());
    }

    @Test
    void test3() {
        Mockito.when(boxRepository.findBoxesWithSizeLessThanAndQuarantine(ArgumentMatchers.anyInt(), ArgumentMatchers.anyBoolean()))
                .thenReturn(Collections.emptyList());
        Assertions.assertNull(animalService.findAvailableBox());
    }

}
