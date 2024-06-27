package com.example.shelter.animal;

import com.example.shelter.box.Box;
import com.example.shelter.mappers.AnimalMapper;
import com.example.shelter.box.BoxRepository;
import com.example.shelter.box.BoxService;
import lombok.RequiredArgsConstructor;
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
    private final BoxRepository boxRepository;

    @Transactional
    public AnimalDTO save(AnimalDTO animalDTO) { //
        Animal newAnimal = animalMapper.toAnimal(animalDTO);
        boxService.addAnimal(newAnimal);
        return animalMapper.toAnimalDTO(animalRepository.save(newAnimal));
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

    public List<AnimalDTO> listAnimals() {
        return animalRepository.findAll()
                .stream()
                .map(animal -> animalMapper.toAnimalDTO(animal))
                .collect(Collectors.toList());
    }

    public List<AnimalDTO> listNonVaccinated() {
        return animalRepository.getNonVaccinated(false)
                .stream()
                .map(animal -> animalMapper.toAnimalDTO(animal))
                .collect(Collectors.toList());
    }

    public List<AnimalDTO> listAvailableForAdoption() {
        return animalRepository.getAvailableForAdoption()
                .stream()
                .map(animal -> animalMapper.toAnimalDTO(animal))
                .collect(Collectors.toList());
    }

    public List<AnimalDTO> listAvailable(Boolean vaccinated, Boolean adopted) {
        return animalRepository.findAllByVaccinatedAndAdopted(vaccinated, adopted)
                .stream()
                .map(animal -> animalMapper.toAnimalDTO(animal))
                .collect(Collectors.toList());
    }

    public List<AnimalDTO> getAnimalByName(String name) {
        return animalRepository.getAnimalByName(name)
                .stream()
                .map(animal -> animalMapper.toAnimalDTO(animal))
                .collect(Collectors.toList());
    }

    public List<AnimalDTO> getByAge(Integer age) {
        return animalRepository.getAnimalByAge(age)
                .stream()
                .map(animal -> animalMapper.toAnimalDTO(animal))
                .collect(Collectors.toList());
    }

    public List<AnimalDTO> getBySex(String sex) {
        return animalRepository.getAnimalBySex(sex)
                .stream()
                .map(animal -> animalMapper.toAnimalDTO(animal))
                .collect(Collectors.toList());
    }

    public List<AnimalDTO> getAnimalsBySize(String size) {
        return animalRepository.getAnimalBySize(size)
                .stream()
                .map(animal -> animalMapper.toAnimalDTO(animal))
                .collect(Collectors.toList());
    }

    public AnimalDTO getAnimalById(Integer id) {
        return animalRepository.findById(id)
                .map(animal-> animalMapper.toAnimalDTO(animal))
                .orElseThrow(()-> new RuntimeException("Nie ma takiego zwierzęcia"));
    }

    public boolean deleteById(Integer AnimalId) {
        Animal foundAnimal = animalRepository.findById(AnimalId).orElseThrow(()-> new RuntimeException("Nie ma takiego zwierzęcia"));
        Box box = boxRepository.findBoxByAnimalId(AnimalId);
        if (box != null) {
            box.getAnimals().remove(foundAnimal);
            boxRepository.save(box);
        }
        animalRepository.deleteById(AnimalId);
        return false;
    }

    public AnimalDTO patchAnimal(AnimalDTO animalDTO) {
        var animal = animalMapper.toAnimal(animalDTO);
        return animalMapper.toAnimalDTO(animalRepository.save(animal));
    }
}