package com.example.shelter.controller;

import com.example.shelter.dto.AnimalDTO;
import com.example.shelter.service.AnimalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/animals")
public class AnimalController {
    private final AnimalService animalService;

    @PostMapping
    public ResponseEntity<AnimalDTO> save(@RequestBody AnimalDTO animalDTO) {
        return ResponseEntity.ok(animalService.save(animalDTO));
    }

    @GetMapping
    public List<AnimalDTO> findAll() {
        return animalService.listAnimals();
    }

    @GetMapping(params = "name")
    // param = tylko wtedy metoda uruchomiona gdy dostarczony jest ten parametr inaczej ambiguance
    public List<AnimalDTO> getAnimalByName(@RequestParam String name) {
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

    @GetMapping("/{id}")
    public AnimalDTO getAnimalById(@PathVariable("id") Integer id) {
        return animalService.getAnimalById(id);
    }

    @GetMapping(params = "vaccinated")
    public List<AnimalDTO> getNonVaccinated(@RequestParam Boolean vaccinated, AnimalDTO animalDTO) {
        return animalService.listNonVaccinated();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Integer id) {
        if (!animalService.deleteById(id)) {
            throw new RuntimeException("Animal not found");
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PatchMapping
    public ResponseEntity<AnimalDTO> update(@RequestBody AnimalDTO animalDTO) {
        return ResponseEntity.ok(animalService.patchAnimal(animalDTO));
    }

    @GetMapping("/adoption")
    public ResponseEntity<?> listReadyForAdoption() {
        return ResponseEntity.ok(animalService.listAvailableForAdoption());
    }
}
// składnia
//localhost:8080/animals?name=Rudy

// /api/v1/animals  -> GET/POST
// /api/v1/animals?maxAge=5&minAge=2 - request params
// /api/v1/animals/id -> GET/PUT/PATCH/DELETE  (tym sposobem robimy update i działemy na jednym)
// /api/v1/owners/id/animals/id

//wysylanie przez parametry zapytania url: animals?parametr1=wartosc&parametr2=wartosc&....  -> @RequestParam lub bez adnotacji
//wysylanie przez czesc sciezki najczesciej do id - @PathVariable
//wysylanie przez cialo zapytania, najczesciej obiekty w formacie json - @RequestBody -> referencja do obiektu DTO
//wysylanie danych w naglowkach zapytania (header) -> dane logowania, login i hasło


