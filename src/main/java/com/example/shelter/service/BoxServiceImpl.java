package com.example.shelter.service;

import com.example.shelter.dto.BoxDTO;
import com.example.shelter.entity.Animal;
import com.example.shelter.entity.Box;
import com.example.shelter.mappers.BoxMapper;
import com.example.shelter.repository.AnimalRepository;
import com.example.shelter.repository.BoxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoxServiceImpl implements BoxService {
    private final BoxRepository boxRepository;
    private final BoxMapper boxMapper;
    private final AnimalRepository animalRepository;

    private int maxAnimalsInBox = 4;

    @Override // overload method, to add animal to box
    public BoxDTO addNewBox(Animal animal, Boolean isQuarantine) {
        Box newBox = saveNewBox(isQuarantine);
        animal.setBox(newBox);
 /*       if (newBox.getAnimals() == null) { // dodane sprawdzenie czy jest zainicjalizowany set
            newBox.setAnimals(new HashSet<>());
        }*/
        newBox.addAnimal(animal); // niepoprawne użycie gettera (newBox.get().add()) ma być dedykowana metoda add
        animalRepository.save(animal);
        boxRepository.save(newBox);
        return boxMapper.toBoxDTO(newBox);
    }

    @Override
    public BoxDTO addNewBox(Boolean isQuarantine) { // for dataInitializer
        return boxMapper.toBoxDTO(saveNewBox(isQuarantine));
    }

    private Box saveNewBox(Boolean isQuarantine) {
        Box newBox = Box.builder()
                .isQuarantine(isQuarantine)
                .boxNumber(findHighestBoxNumber()+1)
                .animals(new HashSet<>())
                .build();
        return boxRepository.save(newBox);
    }

/*    @Override
    public int countAllBoxes() {
        return boxRepository.countAllBoxes();
    }*/

    @Override
    public int findHighestBoxNumber() {
        return boxRepository.giveHighestBoxNumber();
    }

    public Box findAvailableBox(int boxNumber) {
        return boxRepository.findBoxWithSizeLessThanAndBoxNumber(maxAnimalsInBox, boxNumber);
    }

    @Override
    public BoxDTO getBoxById(UUID id) {
        return boxMapper.toBoxDTO(boxRepository.findById(id).orElseThrow());
    }

    @Override
    public BoxDTO getBoxByAnimalId(UUID animalId) {
        return boxMapper.toBoxDTO(boxRepository.findBoxByAnimalId(animalId));
    }

    @Override
    public List<BoxDTO> listBoxes() {
        return boxRepository.findAll()
                .stream()
                .map(box -> boxMapper.toBoxDTO(box))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(UUID id) {
        Box box = boxRepository.findById(id).orElse(null);
        if ((box != null) && (box.getAnimals().isEmpty())) {
            boxRepository.deleteById(id);
        } else {
            throw new BoxServiceException("box with animals impossible to delete, move animals first");
        }
    }

    @Transactional // sprawia że operacja otoczona jest transakcją inaczej hibernate nie widzi otwartej transakcji
    @Override
    public void deleteByNumber(Integer number) {
        Box box = boxRepository.findByNumber(number).orElse(null);
        if ((box != null) && (box.getAnimals().isEmpty())) {
            boxRepository.deleteByNumber(number);
        } else {
            throw new BoxServiceException("box with animals impossible to delete, move animals first");
        }
    }
}
