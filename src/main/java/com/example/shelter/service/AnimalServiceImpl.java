package com.example.shelter.service;

import com.example.shelter.dto.AnimalDTO;
import com.example.shelter.entity.Animal;
import com.example.shelter.entity.Box;
import com.example.shelter.mappers.AnimalMapper;
import com.example.shelter.repository.AnimalRepository;
import com.example.shelter.repository.BoxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnimalServiceImpl implements AnimalService {
    private final AnimalRepository animalRepository;
    private final AnimalMapper animalMapper;
    private final BoxService boxService;
    private final BoxRepository boxRepository;

    @Transactional
    @Override
    public AnimalDTO saveNewAnimal(AnimalDTO animalDTO) { //
        Animal newAnimal = animalMapper.animalDTOToAnimal(animalDTO);
        animalRepository.save(newAnimal);
        boxService.addAnimal(newAnimal);
        return animalMapper.animalToAnimalDTO(animalRepository.save(newAnimal));
    }

    public void vaccinate(Integer id) {
        Animal animal = animalRepository.findById(id).orElseThrow(() -> new AnimalServiceException("Nie ma takiego zwierzęcia"));
        animal.setVaccinated(true);
        animalRepository.save(animal);
    }

    public void walk(Integer id) {
        Animal animal = animalRepository.findById(id).orElseThrow(() -> new AnimalServiceException("Nie ma takiego zwierzęcia"));
        animal.setLastWalkDate(LocalDate.now());
        animalRepository.save(animal);
    }

    public void adopt(Integer id) {
        Animal animal = animalRepository.findById(id).orElseThrow(() -> new AnimalServiceException("Nie ma takiego zwierzęcia"));
        animal.setAdopted(true);
        animalRepository.save(animal);
    }

    @Override
    public List<AnimalDTO> listAnimals() {
        return animalRepository.findAll()
                .stream()
                .map(animal -> animalMapper.animalToAnimalDTO(animal))
                .collect(Collectors.toList());
    }

    public List<AnimalDTO> listNonVaccinated() {
        return animalRepository.getNonVaccinated(false)
                .stream()
                .map(animal -> animalMapper.animalToAnimalDTO(animal))
                .collect(Collectors.toList());
    }

    public List<AnimalDTO> listAvailableForAdoption() {
        return animalRepository.getAvailableForAdoption()
                .stream()
                .map(animal -> animalMapper.animalToAnimalDTO(animal))
                .collect(Collectors.toList());
    }

    public List<AnimalDTO> listAvailable(Boolean vaccinated, Boolean adopted) {
        return animalRepository.findAllByVaccinatedAndAdopted(vaccinated, adopted)
                .stream()
                .map(animal -> animalMapper.animalToAnimalDTO(animal))
                .collect(Collectors.toList());
    }

    @Override
    public List<AnimalDTO> getAnimalByName(String name) {
        return animalRepository.getAnimalByName(name)
                .stream()
                .map(animal -> animalMapper.animalToAnimalDTO(animal))
                .collect(Collectors.toList());
    }

    @Override
    public List<AnimalDTO> getByAge(Integer age) {
        return animalRepository.getAnimalByAge(age)
                .stream()
                .map(animal -> animalMapper.animalToAnimalDTO(animal))
                .collect(Collectors.toList());
    }

    @Override
    public List<AnimalDTO> getBySex(String sex) {
        return animalRepository.getAnimalBySex(sex)
                .stream()
                .map(animal -> animalMapper.animalToAnimalDTO(animal))
                .collect(Collectors.toList());
    }

    @Override
    public List<AnimalDTO> getAnimalsBySize(String size) {
        return animalRepository.getAnimalBySize(size)
                .stream()
                .map(animal -> animalMapper.animalToAnimalDTO(animal))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AnimalDTO> getAnimalById(Integer id) {
        return Optional.ofNullable(animalMapper.animalToAnimalDTO(animalRepository.findById(id).orElseThrow(null)));
    }

    @Override
    public void deleteById(Integer AnimalId) {
        Animal foundAnimal = animalRepository.findById(AnimalId).orElse(null); // znajduję wskazane po AnimalId zwierzę;
        Box box = boxRepository.findBoxByAnimalId(AnimalId); // znajduję box w którym jest dany animal
        if (box != null) {
            box.getAnimals().remove(foundAnimal); // jeśli box się znalazł (zwierzę było przypisane do boxu), usuwam zwierzę
            boxRepository.save(box);
        }
        animalRepository.deleteById(AnimalId); // następnie z tabeli animals (jeśli zwierzę nie było przypisane, również usuwa)
    }

    @Override
    public Optional<AnimalDTO> patchAnimalById(Integer animalId, AnimalDTO animalDTO) {
        return null;
    }
}