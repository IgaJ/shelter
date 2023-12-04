package com.example.shelter.service;

import com.example.shelter.dto.BoxDTO;
import com.example.shelter.entity.Animal;
import com.example.shelter.entity.Box;
import com.example.shelter.mappers.BoxMapper;
import com.example.shelter.repository.AnimalRepository;
import com.example.shelter.repository.BoxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoxServiceImpl implements BoxService {

    private final BoxRepository boxRepository;
    private final BoxMapper boxMapper;
    private final AnimalRepository animalRepository;



    @Override // overload method, to add animal to box
    public BoxDTO saveNewBox(Animal animal, Boolean isQuarantine) {
        Box newBox = new Box();
        newBox.setIsQuarantine(isQuarantine);
        newBox.setNumber(countAllBoxes()+1);
        newBox.getAnimals().add(animal);
        animalRepository.save(animal);
        boxRepository.save(newBox);
        return boxMapper.toBoxDTO(newBox);
    }

    @Override
    public BoxDTO saveNewBox(Boolean isQuarantine) {
        Box newBox = new Box();
        newBox.setIsQuarantine(isQuarantine);
        newBox.setNumber(countAllBoxes()+1); // todo jak nadawać numery nowemu boxowi np. po usunięciu starych
        // daj ostatni z listy, get number, +1.
        return boxMapper.toBoxDTO(boxRepository.save(newBox));
    }

    public int countAllBoxes() {
        return boxRepository.countAllBoxes().orElse(0);
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
            throw new BoxServiceException("box with animals impossible to delete, first move animals");
        }
    }
}
