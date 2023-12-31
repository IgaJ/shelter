package com.example.shelter.controller;

import com.example.shelter.dto.AnimalDTO;
import com.example.shelter.dto.BoxDTO;
import com.example.shelter.service.AnimalService;
import com.example.shelter.service.AnimalServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/animals")
public class AnimalController {
    private final AnimalService animalService;

    @PostMapping
    public ResponseEntity<AnimalDTO> saveNewAnimal(@RequestBody AnimalDTO animalDTO) {
        AnimalDTO savedAnimal = animalService.saveNewAnimal(animalDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/animals/id/" + savedAnimal.getId().toString());
        return new ResponseEntity<>(savedAnimal, headers, HttpStatus.CREATED);
    }

    @GetMapping
    public List<AnimalDTO> listAnimals() {
        return animalService.listAnimals();
    }

    @GetMapping(params = "name")
    // param = tylko wtedy metoda uruchomiona gdy dostarczony jest ten parametr inaczej ambiguance
    public List<AnimalDTO> getAnimalByName(/*@RequestParam*/ String name) {
        return animalService.getAnimalByName(name);
    }

    @GetMapping(params = "age")
    public List<AnimalDTO> getAnimalByAge(@RequestParam Integer age) {
        return animalService.getByAge(age);
    }

    @GetMapping(params = "sex")
    public List<AnimalDTO> getBySex(@RequestParam String sex) {
        return animalService.getBySex(sex);
    }

    @GetMapping(params = "size")
    public List<AnimalDTO> getAnimalsBySize(@RequestParam String size) {
        return animalService.getAnimalsBySize(size);
    }

    @GetMapping("/{id}") // nawias do PathVariable // id zaraz po ukośniku
    public AnimalDTO getAnimalById(@PathVariable("id") UUID id) {
        return animalService.getAnimalById(id).orElseThrow(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") UUID id) {
        animalService.deleteById(id);
        String message = "Deleted: ";
        return new ResponseEntity<>(message + id, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePatchById(@PathVariable("id") UUID animalId, @RequestBody AnimalDTO animalDTO) {
        try {
            AnimalDTO animal = animalService.patchAnimalById(animalId, animalDTO).orElse(null);
            return new ResponseEntity<>(animal, HttpStatus.OK);
        } catch (AnimalServiceException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(400));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBox(@PathVariable("id") UUID animalId, @RequestBody BoxDTO boxDTO) {
        AnimalDTO changed = animalService.changeBoxToGivenBoxNumber(animalId, boxDTO);
        String message = "Box changed to: ";
        return new ResponseEntity<>(message + changed.getBoxNumber(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBox(@PathVariable("id") UUID animalId, @RequestParam ("noQuarantineStatus") String noQuarantineStatus) {
        AnimalDTO changed = animalService.changeBoxToAnyBoxNumberWithNoQuarantineStatus(animalId);
        String message = "Box changed to: ";
        return new ResponseEntity<>(message + changed.getBoxNumber() + " with no quarantine status ", HttpStatus.OK);
    }

/*
    @PutMapping("/{id}")
    public ResponseEntity<String> updateBox(@PathVariable("id") UUID animalId, @RequestParam ("yesQuarantineStatus") String yesQuarantineStatus) { // todo poprawić updateBox2
        AnimalDTO changed = animalService.changeBoxToAnyBoxNumberWithYesQuarantineStatus(animalId);
        String message = "Box changed to: ";
        return new ResponseEntity<>(message + changed.getBoxNumber() + " with yes quarantine status ", HttpStatus.OK);
    }
*/


    @GetMapping(params = "vaccinated")
    public List<AnimalDTO> getNonVaccinated(@RequestParam Boolean vaccinated, AnimalDTO animalDTO) {
        return animalService.listNonVaccinated();
    }

    public ResponseEntity<?> vaccinate(UUID id) { // zmiana cechy robimy updatem zamiast operacji na zasobie
        Optional<AnimalDTO> vaccinated = animalService.vaccinate(id);
        return new ResponseEntity<>(vaccinated.toString(), HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> listReadyForAdoption() {
        List<AnimalDTO> ready = animalService.listAvailableForAdoption();
        return new ResponseEntity<>(ready.stream().toList(), HttpStatus.OK);
    }
}
    // skłądnia
    //localhost:8080/animals?name=Rudy

// /api/v1/animals  -> GET/POST
// /api/v1/animals?maxAge=5&minAge=2 - request params
// /api/v1/animals/id -> GET/PUT/PATCH/DELETE  (tym sposobem robimy update i działemy na jednym)
// /api/v1/owners/id/animals/id

//wysylanie przez parametry zapytania url: animals?parametr1=wartosc&parametr2=wartosc&....  -> @RequestParam lub bez adnotacji
//wysylanie przez czesc sciezki najczesciej do id - @PathVariable
//wysylanie przez cialo zapytania, najczesciej obiekty w formacie json - @RequestBody -> referancja do obiektu DTO
//wysylanie danych w naglowkach zapytania (header) -> dane logowania, login i hasło


