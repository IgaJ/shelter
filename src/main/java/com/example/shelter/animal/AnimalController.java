package com.example.shelter.animal;

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

    @PatchMapping
    public ResponseEntity<AnimalDTO> update(@RequestBody AnimalDTO animalDTO) {
        return ResponseEntity.ok(animalService.update(animalDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        if (!animalService.delete(id)) {
            throw new RuntimeException("Animal not found");
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<AnimalDTO>> listAll() {
        return ResponseEntity.ok(animalService.listAll());
    }

    @PostMapping("/{id}/transfer")
    public ResponseEntity<AnimalDTO> transferToNonQuarantineBox(@PathVariable Integer id) {
        return ResponseEntity.ok(animalService.transferToNonQuarantineBox(id));
    }

    @PostMapping("/{id}/changeBox")
    public ResponseEntity<AnimalDTO> changeBox(@PathVariable Integer id, @RequestParam Integer boxNumber, @RequestParam Boolean isQuarantine) {
        return ResponseEntity.ok(animalService.changeBox(id, boxNumber, isQuarantine));
    }

    @GetMapping("/search")
    public ResponseEntity<List<AnimalDTO>> searchAnimals(@RequestParam(required = false) Integer id,
                                         @RequestParam(required = false) AnimalSpecies animalSpecies,
                                         @RequestParam(required = false) String name,
                                         @RequestParam(required = false) String sex,
                                         @RequestParam(required = false) String size,
                                         @RequestParam(required = false) Integer age) {
        return ResponseEntity.ok(animalService.findBySpecification(id, animalSpecies, name, sex, size,age));
    }

    @GetMapping("/vaccinated")
    public ResponseEntity<List<AnimalDTO>> getNonVaccinated(@RequestParam Boolean vaccinated, AnimalDTO animalDTO) {
        return ResponseEntity.ok(animalService.listNonVaccinated());
    }

    @GetMapping("/adoption")
    public ResponseEntity<List<AnimalDTO>> listReadyForAdoption() {
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


