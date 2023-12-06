package com.example.shelter.service;

import com.example.shelter.dto.BoxDTO;
import com.example.shelter.entity.Animal;
import com.example.shelter.entity.Box;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BoxService {

    BoxDTO getBoxById(UUID id);

    BoxDTO getBoxByAnimalId(UUID animalId);

    List<BoxDTO> listBoxes();

    void deleteById(UUID id);

    BoxDTO addNewBox(Animal animal, Boolean isQuarantine);

    BoxDTO addNewBox(Boolean isQuarantine);

   // public int countAllBoxes();

    public int findBoxWithHigherNumber();

    BoxDTO changeBox(UUID animalId, BoxDTO boxDTO);

    void deleteByNumber(Integer number);
}
