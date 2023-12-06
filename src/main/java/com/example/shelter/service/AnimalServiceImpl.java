package com.example.shelter.service;

import com.example.shelter.entity.Animal;
import com.example.shelter.entity.Box;
import com.example.shelter.mappers.AnimalMapper;
import com.example.shelter.dto.AnimalDTO;
import com.example.shelter.repository.AnimalRepository;
import com.example.shelter.repository.BoxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;
    private final AnimalMapper animalMapper;

    private final BoxService boxService;

    private final BoxRepository boxRepository;
    private int maxAnimalsInBox = 4;  // it can be public static as

    @Transactional
    @Override
    public AnimalDTO saveNewAnimal(AnimalDTO animal) { //
        Animal newAnimal = Animal.builder()
                .species(animal.getSpecies())
                .name(animal.getName())
                .sex(animal.getSex())
                .size(animal.getSize())
                .age(animal.getAge())
                .arrivalDate(animal.getArrivalDate())
                .description(animal.getDescription())
                .build();
        // przydzielane nowego zwierzęcia do boxu, zawsze do kwarantanny
        Box selected = findAvailableBox(); // metoda daje pierwszy box gdzie jest miejsce lub null
        if (selected == null) {
            boxService.addNewBox(newAnimal, true);
        } else {
            if(selected.getAnimals() == null) {
                selected.setAnimals(new HashSet<>());
            }
            selected.addAnimal(newAnimal);
            animalRepository.save(newAnimal);
            boxRepository.save(selected);
        }
        return animalMapper.animalToAnimalDTO(animalRepository.save(newAnimal));
    }

    public Box findAvailableBox() {
        List<Box> availableQuarantineBoxes = boxRepository.findBoxesWithSizeLessThanAndQuarantine(maxAnimalsInBox, true);
        if (!availableQuarantineBoxes.isEmpty()) {
            return availableQuarantineBoxes.get(0);
        } else {
            return null;
        }
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

    public Optional<AnimalDTO> vaccinate(UUID id) {
        Optional<Animal> optionalAnimal = animalRepository.findById(id); // co zrobić z optionalem?
        if (optionalAnimal.isPresent()) {
            Animal animal = optionalAnimal.get();
            animal.setVaccinated(true);
            animal.setVaccinationDate(LocalDateTime.now());
            animalRepository.save(animal);
            return Optional.ofNullable(animalMapper.animalToAnimalDTO(animal));
        } else {
            return Optional.empty();
        }
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
    public Optional<AnimalDTO> getAnimalById(UUID id) {
        return Optional.ofNullable(animalMapper.animalToAnimalDTO(animalRepository.findById(id).orElse(null)));
    }

    @Override
    public void deleteById(UUID AnimalId) {
        Animal foundAnimal = animalRepository.findById(AnimalId).orElse(null); // znajduję wskazane po AnimalId zwierzę;
        Box box = boxRepository.findBoxByAnimalId(AnimalId); // znajduję box w którym jest dany animal
        if (box != null) {
            box.getAnimals().remove(foundAnimal); // jeśli box się znalazł (zwierzę było przypisane do boxu), usuwam zwierzę
            boxRepository.save(box);
        }
        animalRepository.deleteById(AnimalId); // następnie z tabeli animals (jeśli zwierzę nie było przypisane, również usuwa)
    }

    @Override
    public Optional<AnimalDTO> patchAnimalById(UUID animalId, AnimalDTO animalDTO) {
        return null;
    }
}

//@Override
    /*public Optional<AnimalDTO> patchAnimalById(UUID animalId, AnimalDTO animalDTO) { // todo exception zamiast optional
        AtomicReference<Optional<AnimalDTO>> atomicReference = new AtomicReference<>();

        animalRepository.findById(animalId).ifPresentOrElse(foundAnimal -> {

        *//*    if(cosposzlonietak){
                atomicReference.set(Optional.empty());
                return;
            }*//*
            if (animalDTO.getAdopted() != null && animalDTO.getAdopted()) {
                if ((foundAnimal.getBox().getNumber() == 0) || (foundAnimal.getVaccinationDate() == LocalDateTime.now().minus(1, ChronoUnit.YEARS))) { // jeżeli box=0 (kwarantanna) i nie jest szczepione - jeśli data ostatniego szczenienia > rok
                    *//*atomicReference.set(Optional.empty());
                    return;*//*
                    throw new AnimalServiceException("zwierzę nie przygotowane do adopcji");
                }
                foundAnimal.setAdopted(animalDTO.getAdopted());
            }

            if (StringUtils.hasText(animalDTO.getName())) {
                foundAnimal.setName(animalDTO.getName());
            }
            if (StringUtils.hasText(animalDTO.getSex())) {
                foundAnimal.setSex(animalDTO.getSex());
            }
            if (animalDTO.getAge() != null) {
                foundAnimal.setAge(animalDTO.getAge());
            }
            if (animalDTO.getArrivalDate() != null) {
                foundAnimal.setArrivalDate(animalDTO.getArrivalDate());
            }
            if (animalDTO.getBox().getNumber() != null) {
                foundAnimal.getBox().setNumber(animalDTO.getBox().getNumber());
            }
            if (StringUtils.hasText(animalDTO.getDescription())) {
                foundAnimal.setDescription(animalDTO.getDescription());
            }

            if (animalDTO.getVaccinated() != null) {
                foundAnimal.setVaccinated(animalDTO.getVaccinated());
            }
            if (animalDTO.getVaccinationDate() != null) {
                foundAnimal.setVaccinationDate(animalDTO.getVaccinationDate());
            }

            atomicReference.set(Optional.of(animalMapper.animalToAnimalDTO(animalRepository.save(foundAnimal))));
        }, () -> {
            atomicReference.set(Optional.empty());
        });

        return atomicReference.get();
    }*/
