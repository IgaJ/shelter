package com.example.shelter.service;

import com.example.shelter.dto.ActionDTO;
import com.example.shelter.entity.*;
import com.example.shelter.mappers.ActionMapper;
import com.example.shelter.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;
<<<<<<< HEAD
=======

>>>>>>> origin/master
@Service
@RequiredArgsConstructor
public class ActionServiceImpl implements ActionService {
    // akcja na zwierzęciu i boxie. Akcja zawsze dodawana komuś (zwierzęciu...)

<<<<<<< HEAD
    private final ActionRepository actionRepository;
    private final ActionMapper actionMapper;
=======

    private final ActionRepository actionRepository;
    private final ActionMapper actionMapper;

>>>>>>> origin/master
    private final AnimalRepository animalRepository;
    private final BoxRepository boxRepository;
    private final AdoptionRepository adoptionRepository;
    private final VaccinationRepository vaccinationRepository;
    private final WalkRepository walkRepository;

<<<<<<< HEAD
=======

>>>>>>> origin/master
    @Override
    public ActionDTO saveNewAction(ActionDTO actionDTO, UUID id) {
        Action newAction = new Action();
        Animal animal = animalRepository.findById(id).orElseThrow(); // todo exceprtion napisać

        newAction.setActionType(actionDTO.getActionType());
        switch (actionDTO.getActionType()) {
            case ADOPTION -> adopt(animal);
            case VACCINATION -> vaccinate(animal);
            case WALK -> walk(animal);
            case CLEANING -> clean(animal);
        }
        newAction.setDate(LocalDateTime.now());
        animal.getActions().add(newAction);
        return actionMapper.toActionDTO(actionRepository.save(newAction));
    }

    void adopt(Animal animal) {
        animal.setAdopted(true);
<<<<<<< HEAD
=======
        Adoption adoption = animal.getAdoption();
        adoption.setAdoptionDate(LocalDateTime.now());
        adoptionRepository.save(adoption);
>>>>>>> origin/master
    }

    void vaccinate(Animal animal) {
        animal.setVaccinated(true);
<<<<<<< HEAD
        animal.setVaccinationDate(LocalDateTime.now());
=======
        Vaccination vaccination = animal.getVaccination();
        vaccination.setVaccinationDate(LocalDateTime.now());
        vaccinationRepository.save(vaccination);
>>>>>>> origin/master
    }

    void walk(Animal animal) {
        animal.setLastWalkDate(LocalDateTime.now());
<<<<<<< HEAD
=======
        Walk walk = animal.getWalk();
        walk.setAnimal(animal);
        walk.setWalkDate(LocalDateTime.now());
        walkRepository.save(walk);
>>>>>>> origin/master
    }

    void clean(Animal animal) {
        Box box = animal.getBox();
        box.setCleaningDate(LocalDateTime.now());
        boxRepository.save(box);
    }
}
