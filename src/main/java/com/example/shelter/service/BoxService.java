package com.example.shelter.service;

import com.example.shelter.dto.BoxDTO;

import java.util.List;
import java.util.UUID;

public interface BoxService {

    BoxDTO getBoxById(UUID id);

    BoxDTO getBoxByAnimalId(UUID animalId);

    List<BoxDTO> listBoxes();

    void deleteById(UUID id);

    BoxDTO saveNewBox(Boolean isQuarantine, int number);
}
