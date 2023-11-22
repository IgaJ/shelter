package com.example.shelter.service;

import com.example.shelter.dto.BoxDTO;

import java.util.UUID;

public interface BoxService {

    BoxDTO getBoxById(UUID id);

    BoxDTO getBoxByAnimalId(UUID animalId);
}
