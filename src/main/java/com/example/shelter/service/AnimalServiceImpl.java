package com.example.shelter.service;

import com.example.shelter.entity.Animal;
import com.example.shelter.mappers.AnimalMapper;
import com.example.shelter.dto.AnimalDTO;
import com.example.shelter.repository.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;
    private final AnimalMapper animalMapper;



    @Override
    public AnimalDTO saveNewAnimal(AnimalDTO animal) {
        Animal newAnimal = new Animal();
        newAnimal.setSpecies(animal.getSpecies());
        newAnimal.setName(animal.getName());
        newAnimal.setSex(animal.getSex());
        newAnimal.setSize(animal.getSize());
        newAnimal.setAge(animal.getAge());
        newAnimal.setArrivalDate(animal.getArrivalDate());
        newAnimal.getBox().setNumber(0);
        newAnimal.setId(animal.getId());
        newAnimal.setDescription(animal.getDescription());
        newAnimal.setAdopted(false);
        newAnimal.setVaccinated(false);
        return animalMapper.animalToAnimalDTO(animalRepository.save(newAnimal));
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

    public AnimalDTO vaccinate(UUID id) {
        Animal newAnimal = animalRepository.findById(id).orElse(null); // co zrobić z optionalem?
        if (newAnimal != null) {
            newAnimal.setVaccinated(true);
            newAnimal.setVaccinationDate(LocalDateTime.now());
            animalRepository.save(newAnimal);
            return animalMapper.animalToAnimalDTO(newAnimal);
        } else {
            return null;
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
    public Boolean deleteById(UUID id) {
        if (animalRepository.existsById(id)) {
            animalRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<AnimalDTO> patchAnimalById(UUID animalId, AnimalDTO animalDTO) { // todo exception zamiast optional
        AtomicReference<Optional<AnimalDTO>> atomicReference = new AtomicReference<>();

        animalRepository.findById(animalId).ifPresentOrElse(foundAnimal -> {

        /*    if(cosposzlonietak){
                atomicReference.set(Optional.empty());
                return;
            }*/
            if (animalDTO.getAdopted() != null && animalDTO.getAdopted()) {
                if ((foundAnimal.getBox().getNumber() == 0) || (foundAnimal.getVaccinationDate() == LocalDateTime.now().minus(1, ChronoUnit.YEARS))) { // jeżeli box=0 (kwarantanna) i nie jest szczepione - jeśli data ostatniego szczenienia > rok
                    /*atomicReference.set(Optional.empty());
                    return;*/
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
    }
}