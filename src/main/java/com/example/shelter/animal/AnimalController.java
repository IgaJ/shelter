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
                                         @RequestParam(required = false) Integer age,
                                         @RequestParam(required = false) Boolean vaccinated,
                                         @RequestParam(required = false) Boolean available) {
        // better to create specification here than call method with 8 params
        return ResponseEntity.ok(animalService.findBySpecification(id, animalSpecies, name, sex, size,age, vaccinated, available));
    }
}