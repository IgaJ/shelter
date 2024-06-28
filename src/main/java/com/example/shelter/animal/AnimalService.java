package com.example.shelter.animal;

import com.example.shelter.box.Box;
import com.example.shelter.mappers.AnimalMapper;
import com.example.shelter.box.BoxService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnimalService {
    private final AnimalRepository animalRepository;
    private final AnimalMapper animalMapper;
    private final BoxService boxService;

    @Transactional
    public AnimalDTO save(AnimalDTO animalDTO) { //
        Animal newAnimal = animalMapper.toAnimal(animalDTO);
        Box quarantineBox = boxService.findFirstAvailableBox(true).orElseGet(() -> boxService.addNewBox(true));
        quarantineBox.addAnimal(newAnimal);
        return animalMapper.toAnimalDTO(animalRepository.save(newAnimal));
    }

    public AnimalDTO transferToNonQuarantineBox(Integer animalId) {
        Animal animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new RuntimeException("Animal not found"));
        Box nonQuarantineBox = boxService.findFirstAvailableBox(false)
                .orElseThrow(() -> new RuntimeException("No available non-quarantine box found"));
        animal.getBox().getAnimals().remove(animal);
        nonQuarantineBox.addAnimal(animal);
        return animalMapper.toAnimalDTO(animalRepository.save(animal));
    }

    @Transactional
    public AnimalDTO changeBox(Integer animalId, Integer boxNumber, Boolean isQuarantine) {
        Animal animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new RuntimeException("Animal not found"));
        Box newBox = boxService.findBoxByNumberAndQuarantineStatus(boxNumber, isQuarantine)
                .orElseThrow(() -> new RuntimeException("Box not found"));
        animal.getBox().getAnimals().remove(animal);
        newBox.addAnimal(animal);
        return animalMapper.toAnimalDTO(animalRepository.save(animal));
    }

    public void vaccinate(Integer id) {
        Animal animal = animalRepository.findById(id).orElseThrow(() -> new RuntimeException("Nie ma takiego zwierzęcia"));
        animal.setVaccinated(true);
        animalRepository.save(animal);
    }

    public void walk(Integer id) {
        Animal animal = animalRepository.findById(id).orElseThrow(() -> new RuntimeException("Nie ma takiego zwierzęcia"));
        animal.setLastWalkDate(LocalDate.now());
        animalRepository.save(animal);
    }

    public void adopt(Integer id) {
        Animal animal = animalRepository.findById(id).orElseThrow(() -> new RuntimeException("Nie ma takiego zwierzęcia"));
        animal.setAdopted(true);
        animal.setAdoptionDate(LocalDate.now());
        animalRepository.save(animal);
    }

    public List<AnimalDTO> listAll() {
        return animalRepository.findAll()
                .stream()
                .map(animalMapper::toAnimalDTO)
                .collect(Collectors.toList());
    }

    public List<AnimalDTO> listNonVaccinated() {
        return animalRepository.getNonVaccinated(false)
                .stream()
                .map(animalMapper::toAnimalDTO)
                .collect(Collectors.toList());
    }

    public List<AnimalDTO> listAvailableForAdoption() {
        return animalRepository.getAvailableForAdoption()
                .stream()
                .map(animalMapper::toAnimalDTO)
                .collect(Collectors.toList());
    }

    public List<AnimalDTO> findBySpecification(Integer id, AnimalSpecies animalSpecies, String name, String sex, String  size, Integer age) {
        Specification<Animal> spec = Specification.where(AnimalSpecification.hasId(id))
                .and(AnimalSpecification.hasSpecies(animalSpecies))
                .and(AnimalSpecification.hasName(name))
                .and(AnimalSpecification.hasSex(sex))
                .and(AnimalSpecification.hasSize(size))
                .and(AnimalSpecification.hasAge(age));
        return animalRepository.findAll(spec).stream().map(animalMapper::toAnimalDTO).collect(Collectors.toList());
    }

    public boolean delete(Integer AnimalId) {
        Animal foundAnimal = animalRepository.findById(AnimalId).orElseThrow(()-> new RuntimeException("Animal not found"));
        Box box = foundAnimal.getBox();
        if (box != null) {
            box.getAnimals().remove(foundAnimal);
        }
        animalRepository.deleteById(AnimalId);
        return true;
    }

    public AnimalDTO update(AnimalDTO animalDTO) {
        var animal = animalMapper.toAnimal(animalDTO);
        return animalMapper.toAnimalDTO(animalRepository.save(animal));
    }
}