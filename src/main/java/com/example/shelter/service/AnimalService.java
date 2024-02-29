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
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnimalService {
    private final AnimalRepository animalRepository;
    private final AnimalMapper animalMapper;
    private final BoxService boxService;
    private final BoxRepository boxRepository;

    @Transactional
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
        animal.setAdoptionDate(LocalDate.now());
        animalRepository.save(animal);
    }

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

    public List<AnimalDTO> getAnimalByName(String name) {
        return animalRepository.getAnimalByName(name)
                .stream()
                .map(animal -> animalMapper.animalToAnimalDTO(animal))
                .collect(Collectors.toList());
    }

    public List<AnimalDTO> getByAge(Integer age) {
        return animalRepository.getAnimalByAge(age)
                .stream()
                .map(animal -> animalMapper.animalToAnimalDTO(animal))
                .collect(Collectors.toList());
    }

    public List<AnimalDTO> getBySex(String sex) {
        return animalRepository.getAnimalBySex(sex)
                .stream()
                .map(animal -> animalMapper.animalToAnimalDTO(animal))
                .collect(Collectors.toList());
    }

    public List<AnimalDTO> getAnimalsBySize(String size) {
        return animalRepository.getAnimalBySize(size)
                .stream()
                .map(animal -> animalMapper.animalToAnimalDTO(animal))
                .collect(Collectors.toList());
    }

    public Optional<AnimalDTO> getAnimalById(Integer id) {
        return Optional.ofNullable(animalMapper.animalToAnimalDTO(animalRepository.findById(id).orElseThrow(null)));
    }

    public void deleteById(Integer AnimalId) {
        Animal foundAnimal = animalRepository.findById(AnimalId).orElse(null);
        Box box = boxRepository.findBoxByAnimalId(AnimalId);
        if (box != null) {
            box.getAnimals().remove(foundAnimal);
            boxRepository.save(box);
        }
        animalRepository.deleteById(AnimalId);
    }

    public Optional<AnimalDTO> patchAnimal(AnimalDTO animalDTO) {
        Animal existing = animalRepository.getAnimalById(animalDTO.getId());

        if (existing == null) {
            throw new AnimalServiceException("Nie ma zwierzęcia o takim Id");
        }
        if (StringUtils.hasText(animalDTO.getName())) {
            existing.setName(animalDTO.getName());
        }
        if (StringUtils.hasText(animalDTO.getSex())) {
            existing.setSex(animalDTO.getSex());
        }
        if (StringUtils.hasText(animalDTO.getSize())) {
            existing.setSize(animalDTO.getSize());
        }
        if (animalDTO.getAge() != null) {
            existing.setAge(animalDTO.getAge());
        }
        if (animalDTO.getArrivalDate() != null) {
            existing.setArrivalDate(animalDTO.getArrivalDate());
        }
        if (StringUtils.hasText(animalDTO.getDescription())) {
            existing.setDescription(animalDTO.getDescription());
        }
        if (animalDTO.getAdopted() != null) {
            existing.setAdopted(animalDTO.getAdopted());
        }
        if (animalDTO.getVaccinated() != null) {
            existing.setVaccinated(animalDTO.getVaccinated());
        }
        if (animalDTO.getVaccinationDate() != null) {
            existing.setVaccinationDate(animalDTO.getVaccinationDate());
        }
        if (animalDTO.getAdoptionDate() != null) {
            existing.setAdoptionDate(animalDTO.getAdoptionDate());
        }
        if (animalDTO.getLastWalkDate() != null) {
            existing.setLastWalkDate(animalDTO.getLastWalkDate());
        }
        return Optional.ofNullable(animalMapper.animalToAnimalDTO(animalRepository.save(existing)));
    }
}