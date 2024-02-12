package com.example.shelter.service;

import com.example.shelter.dto.ActionDTO;
import com.example.shelter.entity.Action;
import com.example.shelter.entity.Animal;
import com.example.shelter.entity.Box;
import com.example.shelter.mappers.ActionMapper;
import com.example.shelter.mappers.AnimalMapper;
import com.example.shelter.mappers.BoxMapper;
import com.example.shelter.repository.ActionRepository;
import com.example.shelter.repository.BoxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActionServiceImpl implements ActionService {

    private final ActionRepository actionRepository;
    private final ActionMapper actionMapper;
    private final AnimalService animalService;
    private final AnimalMapper animalMapper;
    private final BoxService boxService;
    private final BoxMapper boxMapper;


    @Override
    public ActionDTO saveNewActionForAnimal(ActionDTO actionDTO) {
        Animal animal = (animalMapper.animalDTOToAnimal(animalService.getAnimalById(actionDTO.getAnimalId()).orElseThrow(() -> new ActionServiceException("Nie ma takiego zwierzęcia"))));
        Action newAction = new Action();
        newAction.setActionType(actionDTO.getActionType());
        switch (actionDTO.getActionType()) {
            //case ADMIT -> admit(animal);
            //case ADOPT -> adopt(animal);
            case VACCINATE -> animalService.vaccinate(animal.getId());
            case WALK -> animalService.walk(animal.getId());
        }
        newAction.setActionDate(LocalDate.now());
        newAction.setAnimal(animal);
        animal.addAction(newAction);
        actionRepository.save(newAction);
        return actionMapper.toActionDTO(newAction);
    }

    @Override
    public ActionDTO saveNewActionForBox(ActionDTO actionDTO) {
        Box box = (boxMapper.toBox(boxService.getBoxById(actionDTO.getBoxId())));
        Action newAction = new Action();
        newAction.setActionType(actionDTO.getActionType());
        switch (actionDTO.getActionType()) {
            case CLEAN -> boxService.clean(actionDTO.getId());
        }
        newAction.setActionDate(LocalDate.now());
        newAction.setBox(box);
        box.addAction(newAction);
        actionRepository.save(newAction);
        return actionMapper.toActionDTO(newAction);
    }

    @Override
    public List<ActionDTO> getActionsByAnimalId(Integer id) {
        return actionRepository.findActionsByAnimalId(id)
                .stream()
                .map(action -> actionMapper.toActionDTO(action))
                .collect(Collectors.toList());
    }

    private void admit(Animal animal) {
    }

    private void adopt(Animal animal) {
        animal.setAdopted(true);
        animal.setAdoptionDate(LocalDate.now());
    }

    public List<ActionDTO> listActions() {
        return actionRepository.findAll()
                .stream()
                .map(action -> actionMapper.toActionDTO(action))
                .collect(Collectors.toList());
    }
}
