package com.example.shelter.task;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    @PatchMapping
    public ResponseEntity<TaskDTO> update(@RequestBody TaskDTO taskDTO) {
        return ResponseEntity.ok(taskService.update(taskDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        if (!taskService.delete(id)) {
            throw new RuntimeException("Task not found");
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> listAll() {
        return ResponseEntity.ok(taskService.listAll());
    }

    @GetMapping("/by-date")
    public ResponseEntity<List<TaskDTO>> findTasksByDate(@RequestParam LocalDate date) {
        return ResponseEntity.ok(taskService.findByDate(date));
    }

    @GetMapping("/by-type")
    public ResponseEntity<List<TaskDTO>> findTasksByType(@RequestParam TaskType taskType) {
        return ResponseEntity.ok(taskService.findByType(taskType));
    }
}
