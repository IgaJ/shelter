package com.example.shelter.box;

import com.example.shelter.mappers.BoxMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoxService {
    private final BoxRepository boxRepository;
    private final BoxMapper boxMapper;

    public Optional<Box> findFirstAvailableBox(Boolean isQuarantine) {
        return boxRepository.findFirstAvailable(isQuarantine);
    }

    public Optional<Box> findBoxByNumberAndQuarantineStatus(Integer boxNumber, Boolean isQuarantine) {
        return boxRepository.findByBoxNumberAndIsQuarantine(boxNumber, isQuarantine);
    }

    public void addNewBox(Boolean isQuarantine) {
        Box newBox = Box.builder()
                .isQuarantine(isQuarantine)
                .boxNumber(findHighestBoxNumber() + 1)
                .animals(new HashSet<>())
                .build();
        boxRepository.save(newBox);
    }

    public int findHighestBoxNumber() {
        return boxRepository.giveHighestBoxNumber();
    }

    public Box findByAnimalId(Integer id) {
        return boxRepository.findByAnimalId(id);
    }



    public void clean(Integer id) {
        Box box = boxRepository.getReferenceById(id);
        box.setCleaningDate(LocalDate.now());
        boxRepository.save(box);
    }

    public BoxDTO getBoxById(Integer id) {
        return boxMapper.toBoxDTO(boxRepository.findById(id).orElseThrow());
    }

    public List<BoxDTO> listBoxes() {
        return boxRepository.findAll()
                .stream()
                .map(box -> boxMapper.toBoxDTO(box))
                .collect(Collectors.toList());
    }

    public void deleteById(Integer id) {
        Box box = boxRepository.findById(id).orElseThrow(() -> new RuntimeException("Box not found"));
        if (box.getAnimals().isEmpty()) {
            boxRepository.deleteById(id);
        } else {
            throw new RuntimeException("box with animals impossible to delete, move animals first");
        }
    }

    public List<BoxDTO> findBoxesWithPlace() {
        return boxRepository.findBoxesWithNumberOfAnimalsLessThanBoxCapacity()
                .stream()
                .map(box -> boxMapper.toBoxDTO(box))
                .collect(Collectors.toList());
    }
}
