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

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoxServiceImpl implements BoxService {
    private final BoxRepository boxRepository;
    private final BoxMapper boxMapper;
    private final AnimalRepository animalRepository;

    public void addAnimal(Animal animal) {
        Optional<Box> selected = findAvailableBoxWithSizeAndQuarantine();
        if (selected.isEmpty()) {
            addNewBox(animal, true);
        } else {
            selected.get().addAnimal(animal); // .get do wyciągnięcia boksu z optionala
            boxRepository.save(selected.get());
        }
     /*   findAvailableBoxWithSizeAndQuarantine()
                .map(box ->{
                    box.addAnimal(animal);
                  return  boxMapper.toBoxDTO(boxRepository.save(box));
                }).or(()->{
                    addNewBox(animal,true);
                    return null;
                });*/
    }

    public void changeBoxToGivenBoxNumber(Integer animalId, Integer boxId) {
        Animal animal = animalRepository.getAnimalById(animalId);
        Box currentBox = animal.getBox();
        Box box = boxRepository.findByNumber(boxId)
                .orElseThrow(() -> new BoxServiceException("Błędny numer boksu")); // rozwinąć obsługę przypadku gdy nie ma numeru/nie ma miejsc - lista dostępnych?
        if (box.getAnimals().size() < box.getMaxAnimals()) {
            box.addAnimal(animal);
            currentBox.getAnimals().remove(animal);
            boxRepository.save(box);
            boxRepository.save(currentBox);
            animalRepository.save(animal);
        } else {
            throw new BoxServiceException("W boksie nie ma miejsc");
        }
    }

    public void changeBoxToFirstQuarantineBox(Integer animalId) {
        Animal animal = animalRepository.getAnimalById(animalId);
        Box currentBox = animal.getBox();
        currentBox.getAnimals().remove(animal);
        addAnimal(animal);
        boxRepository.save(currentBox);
    }

    public void changeBoxToFirstNoQuarantineBox(Integer animalId) {
        Animal animal = animalRepository.getAnimalById(animalId);
        Box currentBox = animal.getBox();
        currentBox.getAnimals().remove(animal);
        boxRepository.save(currentBox);
        Optional<Box> newBox = boxRepository.findBoxesWithSizeLessThanBoxCapacityAndQuarantine(false).stream().findFirst();
        if (newBox.isEmpty()) {
            addNewBox(animal, false);
        } else {
            Box optional = newBox.get();
            optional.addAnimal(animal);
            boxRepository.save(optional);
        }
    }

    Optional<Box> findAvailableBoxWithSizeAndQuarantine() {
        return boxRepository.findBoxesWithSizeLessThanBoxCapacityAndQuarantine(true)
                .stream()
                .findFirst();
    }

    @Override
    public BoxDTO addNewBox(Boolean isQuarantine) { // only for dataInitializer
        return boxMapper.toBoxDTO(saveNewBox(isQuarantine));
    }

    @Override // overload method, to add animal to box
    public BoxDTO addNewBox(Animal animal, Boolean isQuarantine) {
        Box newBox = saveNewBox(isQuarantine);
        animal.setBox(newBox);
        newBox.addAnimal(animal);
        animalRepository.save(animal);
        boxRepository.save(newBox);
        return boxMapper.toBoxDTO(newBox);
    }

    private Box saveNewBox(Boolean isQuarantine) {
        Box newBox = Box.builder()
                .isQuarantine(isQuarantine)
                .boxNumber(findHighestBoxNumber() + 1)
                .animals(new HashSet<>())
                .build();
        return boxRepository.save(newBox);
    }

    @Override
    public int findHighestBoxNumber() {
        return boxRepository.giveHighestBoxNumber();
    }

    public void clean(Integer id) {
        Box box = boxRepository.getReferenceById(id);
        box.setCleaningDate(LocalDate.now());
        boxRepository.save(box);
    }

    @Override
    public BoxDTO getBoxById(Integer id) {
        return boxMapper.toBoxDTO(boxRepository.findById(id).orElseThrow());
    }

    @Override
    public BoxDTO getBoxByAnimalId(Integer animalId) {
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
    public void deleteById(Integer id) {
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
            if (box == null) {
                throw new BoxServiceException("Invalid box number");
            }
            if (!box.getAnimals().isEmpty()) {
                throw new BoxServiceException("Animals in box to be deleted");
            }
        }
    }

    @Override
    public BoxDTO findBoxWithSizeLessThanBoxCapacityAndBoxNumber(Integer boxNumber) {
        return boxMapper.toBoxDTO(boxRepository.findBoxWithSizeLessThanBoxCapacityAndBoxNumber(boxNumber));
    }

    @Override
    public List<BoxDTO> findBoxesWithPlace() {
        return boxRepository.findBoxesWithNumberOfAnimalsLessThanBoxCapacity()
                .stream()
                .map(box -> boxMapper.toBoxDTO(box))
                .collect(Collectors.toList());
    }
}
