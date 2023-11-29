package com.example.shelter.service;

import com.example.shelter.dto.ActionDTO;
import com.example.shelter.entity.*;
import com.example.shelter.mappers.ActionMapper;
import com.example.shelter.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ActionServiceImpl implements ActionService {
    // akcja na zwierzęciu i boxie. Akcja zawsze dodawana komuś (zwierzęciu...)

    private final ActionRepository actionRepository;
    private final ActionMapper actionMapper;
    private final AnimalRepository animalRepository;
    private final BoxRepository boxRepository;



    @Override
    public ActionDTO saveNewAction(ActionDTO actionDTO, UUID id) {
        Animal animal = animalRepository.findById(id).orElseThrow(); // todo exceprtion napisać
        animalRepository.save(animal);

        Action newAction = new Action();
        newAction.setActionType(actionDTO.getActionType());
        switch (actionDTO.getActionType()) {
            //case ADMISSION -> admiss(animal);
            case ADOPTION -> adopt(animal);
            case VACCINATION -> vaccinate(animal);
            case WALK -> walk(animal);
            case CLEANING -> clean(animal);
        }
        newAction.setDate(LocalDateTime.now());
        animal.getActions().add(newAction);
        actionRepository.save(newAction);
        return actionMapper.toActionDTO(newAction);
    }

/*
    private void admiss(Animal animal) {
        animal.get
    }
*/

    void adopt(Animal animal) {
        animal.setAdopted(true);
        animal.setAdoptionDate(LocalDateTime.now());
    }

    void vaccinate(Animal animal) {
        animal.setVaccinated(true);
        animal.setVaccinationDate(LocalDateTime.now());
    }

    void walk(Animal animal) {
        animal.setLastWalkDate(LocalDateTime.now());
    }

    void clean(Animal animal) {
        Box box = boxRepository.findBoxByAnimalId(animal.getId());
        box.setCleaningDate(LocalDateTime.now());
        boxRepository.save(box);
    }
}
