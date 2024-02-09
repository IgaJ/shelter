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
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActionServiceImpl implements ActionService {
    // akcja na zwierzęciu i boxie. Akcja zawsze dodawana komuś (zwierzęciu...)

    private final ActionRepository actionRepository;
    private final ActionMapper actionMapper;
    private final AnimalRepository animalRepository;
    private final BoxRepository boxRepository; // serwisy zamiast repozytoriów tutaj
    private final AnimalService animalService;



    @Override
    public ActionDTO saveNewActionForAnimal(ActionDTO actionDTO) {
        Animal animal = animalRepository.findById(actionDTO.getAnimalId()).orElseThrow(() -> new ActionServiceException("Nie ma takiego zwierzęcia"));
        Integer animalId = animal.getId();
        Action newAction = new Action();
        newAction.setActionType(actionDTO.getActionType());
        switch (actionDTO.getActionType()) {
            case ADMIT -> admit(actionDTO);
            case ADOPT -> adopt(animal);
            case VACCINATE -> vaccinate(animalId);
            case WALK -> walk(animal);
        }
        newAction.setActionDate(LocalDate.now());
        newAction.setAnimal(animal);
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

    @Override
    public List<ActionDTO> getActionsByAnimalId(Integer id) {
        return actionRepository.findActionsByAnimalId(id)
                .stream()
                .map(action -> actionMapper.toActionDTO(action))
                .collect(Collectors.toList());
    }

    private void admit(ActionDTO actionDTO) {
        //animalService.saveNewAnimal(actionMapper.toAnimalDTO(actionDTO));
    }

    private void adopt(Animal animal) {
        animal.setAdopted(true);
        animal.setAdoptionDate(LocalDate.now());
    }

    private void vaccinate(Integer animalId) {
        animalService.vaccinate(animalId);
    }

    private void walk(Animal animal) {
        animal.setLastWalkDate(LocalDate.now());
    }

    private void clean(Box box) {
        box.setCleaningDate(LocalDate.now());
    }

    public List<ActionDTO> listActions() {
        return actionRepository.findAll()
                .stream()
                .map(action -> actionMapper.toActionDTO(action))
                .collect(Collectors.toList());
    }
}
