package com.example.shelter.animal;

import com.example.shelter.task.TaskDTO;
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
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        if (!animalService.delete(id)) {
            throw new RuntimeException("Animal not found");
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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

    @PostMapping("/search")
    public ResponseEntity<List<AnimalDTO>> searchAnimals(@RequestBody AnimalSearchCriteria criteria) {
        return ResponseEntity.ok(animalService.searchAnimals(criteria));
    }

    @PostMapping("/{id}/feed")
    public ResponseEntity<AnimalDTO> feed(@PathVariable Integer id) {
        AnimalDTO fed = animalService.feed(id);
        return ResponseEntity.ok(fed);
    }

    @PostMapping("/{id}/walk")
    public ResponseEntity<AnimalDTO> walk(@PathVariable Integer id) {
        AnimalDTO walked = animalService.walk(id);
        return ResponseEntity.ok(walked);
    }

    @PostMapping("/{id}/health")
    public ResponseEntity<AnimalDTO> checkHealth(@PathVariable Integer id) {
        AnimalDTO checked = animalService.checkHealth(id);
        return ResponseEntity.ok(checked);
    }

    @PostMapping("/{id}/vaccinate")
    public ResponseEntity<AnimalDTO> vaccinate(@PathVariable Integer id) {
        AnimalDTO vaccinated = animalService.vaccinate(id);
        return ResponseEntity.ok(vaccinated);
    }

    @PostMapping("/{id}/adopt")
    public ResponseEntity<AnimalDTO> adopt(@PathVariable Integer id) {
        AnimalDTO adopted = animalService.adopt(id);
        return ResponseEntity.ok(adopted);
    }
    @GetMapping("/{id}/tasks")
    public List<TaskDTO> getTasksForAnimal(@PathVariable Integer id) {
        return animalService.getTasksForAnimal(id);
    }
}