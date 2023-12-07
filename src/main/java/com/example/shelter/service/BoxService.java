package com.example.shelter.service;

import com.example.shelter.dto.BoxDTO;
import com.example.shelter.entity.Animal;

import java.util.List;
import java.util.UUID;

public interface BoxService {

    BoxDTO getBoxById(UUID id);

    BoxDTO getBoxByAnimalId(UUID animalId);

    List<BoxDTO> listBoxes();

    void deleteById(UUID id);

    BoxDTO addNewBox(Animal animal, Boolean isQuarantine);

    BoxDTO addNewBox(Boolean isQuarantine);

   // public int countAllBoxes();

    public int findHighestBoxNumber();

    void deleteByNumber(Integer number);
}
