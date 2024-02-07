package com.example.shelter.service;

import com.example.shelter.dto.ActionDTO;
import com.example.shelter.entity.Action;
import com.example.shelter.entity.Animal;
import com.example.shelter.entity.Box;
import com.example.shelter.mappers.ActionMapper;
import com.example.shelter.repository.ActionRepository;
import com.example.shelter.repository.AnimalRepository;
import com.example.shelter.repository.BoxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ActionServiceImpl implements ActionService {
    // akcja na zwierzęciu i boxie. Akcja zawsze dodawana komuś (zwierzęciu...)

    private final ActionRepository actionRepository;
    private final ActionMapper actionMapper;
    private final AnimalRepository animalRepository;
    private final BoxRepository boxRepository; // serwisy zamiast repozytoriów tutaj



    @Override
    public ActionDTO saveNewActionForAnimal(ActionDTO actionDTO) {
        Animal animal = animalRepository.findById(actionDTO.getAnimalId()).orElseThrow(() -> new ActionServiceException("Nie ma takiego zwierzęcia"));
        Action newAction = new Action();
        newAction.setActionType(actionDTO.getActionType());
        switch (actionDTO.getActionType()) {
            case ADMIT -> admit(animal);
            case ADOPT -> adopt(animal);
            case VACCINATE -> vaccinate(animal);
            case WALK -> walk(animal);
        }
        newAction.setActionDate(LocalDate.now());
        animal.addAction(newAction);
        actionRepository.save(newAction);
        animalRepository.save(animal);
        return actionMapper.toActionDTO(newAction);
    }

    @Override
    public ActionDTO saveNewActionForBox(ActionDTO actionDTO) {
        Box box = boxRepository.findById(actionDTO.getBoxId()).orElseThrow(()-> new ActionServiceException("Nie ma takiego boksu"));
        Action newAction = new Action();
        newAction.setActionType(actionDTO.getActionType());
        switch (actionDTO.getActionType()) {
            case CLEAN -> clean(box);
        }
        newAction.setActionDate(LocalDate.now());
        box.addAction(newAction);
        actionRepository.save(newAction); // najpierw save akcji potem boxu (bo w boxie wskazanie na akcje)
        boxRepository.save(box);
        return actionMapper.toActionDTO(newAction);
    }

    private void admit(Animal animal) {
        animal.setArrivalDate(LocalDate.now());
        animal.setAdopted(false);
        animal.setVaccinated(false);
    }

    private void adopt(Animal animal) {
        animal.setAdopted(true);
        animal.setAdoptionDate(LocalDate.now());
    }

    private void vaccinate(Animal animal) {
        animal.setVaccinated(true);
    }

    private void walk(Animal animal) {
        animal.setLastWalkDate(LocalDate.now());
    }

    private void clean(Box box) {
        box.setCleaningDate(LocalDate.now());
    }
}
