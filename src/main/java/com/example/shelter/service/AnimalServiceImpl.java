package com.example.shelter.service;

import com.example.shelter.dto.AnimalDTO;
import com.example.shelter.dto.BoxDTO;
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
        Animal newAnimal = Animal.builder()
                .species(animalDTO.getSpecies())
                .name(animalDTO.getName())
                .sex(animalDTO.getSex())
                .size(animalDTO.getSize())
                .age(animalDTO.getAge())
                .arrivalDate(animalDTO.getArrivalDate())
                .description(animalDTO.getDescription())
                .build();
        // automatyczne przydzielane nowego zwierzęcia do boxu, zawsze do kwarantanny
        Box selected = findAvailableBoxWithSizeAndQuarantine(); // metoda daje pierwszy box gdzie jest miejsce lub null
        if (selected == null) {
            boxService.addNewBox(newAnimal, true);
        } else {
            /*if(selected.getAnimals() == null) {
                selected.setAnimals(new HashSet<>());
            }*/
            selected.addAnimal(newAnimal);
            animalRepository.save(newAnimal);
            boxRepository.save(selected);
        }
        return animalMapper.animalToAnimalDTO(animalRepository.save(newAnimal));
    }

    Box findAvailableBoxWithSizeAndQuarantine() {
        List<Box> availableQuarantineBoxes = boxRepository.findBoxesWithSizeLessThanBoxCapacityAndQuarantine(true);
        Optional<Box> requested = availableQuarantineBoxes.stream()
                .findFirst();
        return requested.orElse(null);
    }

    // 1. Ręczna zmiana boksu: znajdż box gdzie zwierząt <4 i ma podany numer (bzwzgl na kwarantannę)
    // 2. Automatyczna zmian boksu: znajdź pierwszy gdzie zwierząt <4, i jest wskazana kwarantanna - gdy pracownik nie chce wskazywać konkretnego
    // 2a. kwarantanna nie
    // 2b. kwarantanna tak
    //
    // 1a. zwróć box o podanym numerze - exception nie ma takiego numeru boxu
    // 1b. spr czy są miejsca, jeśli nie to exception, jeśli tak to addAnimal
    // 2a. zwroć pierwszy gdzie zwierząt <4
    // 2b. gdy nie ma boxu z wolnymi miejscami - stwórz nowy box


    public AnimalDTO changeBoxToGivenBoxNumber(Integer animalId, BoxDTO boxDTO) { // 1.
        Animal animal = animalRepository.getAnimalById(animalId);
        Box currentBox = animal.getBox();
        Box box = boxRepository.findByNumber(boxDTO.getBoxNumber())
                .orElseThrow(() -> new BoxServiceException("We wskazanym boksie nie ma miejsca")); // rozwinąć obsługę przypadku gdy nie ma numeru/nie ma miejsc - lista dostępnych?
        if (box.getAnimals().size() < box.getMaxAnimals()) {
            box.addAnimal(animal);
            currentBox.getAnimals().remove(animal);
            boxRepository.save(box);
            boxRepository.save(currentBox);
            animalRepository.save(animal);
            return animalMapper.animalToAnimalDTO(animal);
        } else {
            throw new BoxServiceException("Nie ma boksu o wskazanym numerze");
        }
    }

    public AnimalDTO changeBoxToAnyBoxNumberWithNoQuarantineStatus(Integer animalId) { // 2a
        Animal animal = animalRepository.getAnimalById(animalId);
        Box currentBox = animal.getBox();
        Box newBox = findFirstBoxWithPlaceAndNoQuarantine();
        if (newBox == null) {
            currentBox.getAnimals().remove(animal);
            boxService.addNewBox(animal, false);
            return animalMapper.animalToAnimalDTO(animal);
        } else {
            currentBox.getAnimals().remove(animal);
            newBox.addAnimal(animal);
            boxRepository.save(currentBox);
            boxRepository.save(newBox);
            animalRepository.save(animal);
            return animalMapper.animalToAnimalDTO(animal);
        }
    }

    public AnimalDTO changeBoxToAnyBoxNumberWithYesQuarantineStatus(Integer animalId) { // 2b
        Animal animal = animalRepository.getAnimalById(animalId);
        Box currentBox = animal.getBox();
        Box newBox = findFirstBoxWithPlaceAndWithQuarantine();
        if (newBox == null) {
            currentBox.getAnimals().remove(animal);
            boxService.addNewBox(animal, false);
            return animalMapper.animalToAnimalDTO(animal);
        } else {
            currentBox.getAnimals().remove(animal);
            newBox.addAnimal(animal);
            boxRepository.save(currentBox);
            boxRepository.save(newBox);
            animalRepository.save(animal);
            return animalMapper.animalToAnimalDTO(animal);
        }
    }

    public Box findFirstBoxWithPlaceAndNoQuarantine() { // pierwszy dowolny box gdzie jest miejsce oraz nie jest kwarantanną
        List<Box> boxes = boxRepository.findBoxesWithNumberOfAnimalsLessThanBoxCapacity();
        return boxes.stream()
                .filter(box -> box.getIsQuarantine().equals(false))
                .findFirst().orElseThrow(() -> new BoxServiceException("Nie ma wolnego boksu bez kwarantanny"));
    }

    public Box findFirstBoxWithPlaceAndWithQuarantine() { // pierwszy dowolny box gdzie jest miejsce oraz jest kwarantanną
        List<Box> boxes = boxRepository.findBoxesWithNumberOfAnimalsLessThanBoxCapacity();
        return boxes.stream()
                .filter(box -> box.getIsQuarantine().equals(true))
                .findFirst().orElseThrow(() -> new BoxServiceException("Nie ma wolnego boksu z kwarantanną"));
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

//@Override
    /*public Optional<AnimalDTO> patchAnimalById(UUID animalId, AnimalDTO animalDTO) {
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
