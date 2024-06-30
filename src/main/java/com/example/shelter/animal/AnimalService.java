package com.example.shelter.animal;

import com.example.shelter.box.Box;
import com.example.shelter.box.BoxService;
import com.example.shelter.mappers.AnimalMapper;
import com.example.shelter.task.*;
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
    private final TaskService taskService;

    @Transactional
    public AnimalDTO save(AnimalDTO animalDTO) {
        Animal newAnimal = animalMapper.toAnimal(animalDTO);
        Box quarantineBox = boxService.findFirstAvailableBox(true).orElseGet(() -> boxService.addNewBox(true));
        quarantineBox.addAnimal(newAnimal);
        return animalMapper.toAnimalDTO(animalRepository.save(newAnimal));
    }

    public AnimalDTO update(AnimalDTO animalDTO) {
        var animal = animalMapper.toAnimal(animalDTO);
        return animalMapper.toAnimalDTO(animalRepository.save(animal));
    }

    public boolean delete(Integer AnimalId) {
        Animal foundAnimal = animalRepository.findById(AnimalId).orElseThrow(() -> new RuntimeException("Animal not found"));
        Box box = foundAnimal.getBox();
        if (box != null) {
            box.getAnimals().remove(foundAnimal);
        }
        animalRepository.deleteById(AnimalId);
        return true;
    }

    public List<AnimalDTO> listAll() {
        return animalRepository.findAll()
                .stream()
                .map(animalMapper::toAnimalDTO)
                .collect(Collectors.toList());
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

    public List<AnimalDTO> findBySpecification(Integer id, AnimalSpecies animalSpecies, String name, String sex, String size, Integer age, Boolean vaccinated, Boolean available) {
        Specification<Animal> spec = Specification.where(AnimalSpecification.hasId(id))
                .and(AnimalSpecification.hasSpecies(animalSpecies))
                .and(AnimalSpecification.hasName(name))
                .and(AnimalSpecification.hasSex(sex))
                .and(AnimalSpecification.hasSize(size))
                .and(AnimalSpecification.hasAge(age))
                .and(AnimalSpecification.isVaccinated(vaccinated))
                .and(AnimalSpecification.isAvailableForAdoption(available));
        return animalRepository.findAll(spec).stream().map(animalMapper::toAnimalDTO).collect(Collectors.toList());
    }

    @Transactional
    public AnimalDTO feed(Integer id) {
        Animal animal = animalRepository.findById(id).orElseThrow(() -> new RuntimeException("Animal not found"));
        animal.setFeedDate(LocalDate.now());
        animalRepository.save(animal);

        String description = "Feeding of " + animal.getName() + " (animal id: " + animal.getId() + ")";
        taskService.saveTaskForAnimal(animal, description, TaskType.FEEDING);
        return animalMapper.toAnimalDTO(animal);
    }

    @Transactional
    public AnimalDTO walk(Integer id) {
        Animal animal = animalRepository.findById(id).orElseThrow(() -> new RuntimeException("Animal not found"));
        animal.setWalkDate(LocalDate.now());
        animalRepository.save(animal);

        String description = "Walk with " + animal.getName() + " (animal id: " + animal.getId() + ")";
        taskService.saveTaskForAnimal(animal, description, TaskType.WALK);
        return animalMapper.toAnimalDTO(animal);
    }

    @Transactional
    public AnimalDTO checkHealth(Integer id) {
        Animal animal = animalRepository.findById(id).orElseThrow(() -> new RuntimeException("Animal not found"));
        animal.setHealthCheckDate(LocalDate.now());
        animalRepository.save(animal);

        String description = "Health check of " + animal.getName() + " (animal id: " + animal.getId() + ")";
        taskService.saveTaskForAnimal(animal, description, TaskType.HEALTH_CHECK);
        return animalMapper.toAnimalDTO(animal);
    }

    @Transactional
    public AnimalDTO vaccinate(Integer id) {
        Animal animal = animalRepository.findById(id).orElseThrow(() -> new RuntimeException("Animal not found"));
        animal.setVaccinated(true);
        animal.setVaccinationDate(LocalDate.now());
        animalRepository.save(animal);

        String description = "Vaccination of " + animal.getName() + " (animal id: " + animal.getId() + ")";
        taskService.saveTaskForAnimal(animal, description, TaskType.VACCINATION);
        return animalMapper.toAnimalDTO(animal);
    }

    @Transactional
    public AnimalDTO adopt(Integer id) {
        Animal animal = animalRepository.findById(id).orElseThrow(() -> new RuntimeException("Animal not found"));
        animal.setAdopted(true);
        animal.setAdoptionDate(LocalDate.now());
        animalRepository.save(animal);

        String description = "Adoption of " + animal.getName() + " (animal id: " + animal.getId() + ")";
        taskService.saveTaskForAnimal(animal, description, TaskType.ADOPTION);
        return animalMapper.toAnimalDTO(animal);
    }

    @Transactional
    public List<TaskDTO> getTasksForAnimal(Integer animalId) {
        if (!animalRepository.existsById(animalId)) {
            throw new RuntimeException("Animal not found");
        }
        return taskService.findByAnimalId(animalId);
    }
}