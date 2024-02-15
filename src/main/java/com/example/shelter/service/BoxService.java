package com.example.shelter.service;

import com.example.shelter.dto.BoxDTO;
import com.example.shelter.entity.Animal;

import java.util.List;

public interface BoxService {

    BoxDTO getBoxById(Integer id);

    void addAnimal(Animal animal);
    void changeBoxToGivenBoxNumber(Integer animalId, Integer boxId);
    void changeBoxToFirstQuarantineBox(Integer animalId);
    public void changeBoxToFirstNoQuarantineBox(Integer animalId);

    BoxDTO getBoxByAnimalId(Integer animalId);

    List<BoxDTO> listBoxes();

    void deleteById(Integer id);

    BoxDTO addNewBox(Animal animal, Boolean isQuarantine);

    BoxDTO addNewBox(Boolean isQuarantine);

   // public int countAllBoxes();

    int findHighestBoxNumber();

    void deleteByNumber(Integer number);

    BoxDTO findBoxWithSizeLessThanBoxCapacityAndBoxNumber(Integer boxNumber);

    List<BoxDTO> findBoxesWithPlace();

    void clean(Integer Id);
}
