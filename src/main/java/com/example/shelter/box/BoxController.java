package com.example.shelter.box;

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
@RequestMapping("api/v1/boxes")
public class BoxController {
    private final BoxService boxService;



    @GetMapping(produces = "application/json")
    public ResponseEntity<List<BoxDTO>> findAll() {
        return ResponseEntity.ok(boxService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoxDTO> getBoxById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(boxService.getBoxById(id));
    }

    @GetMapping("/available")
    public ResponseEntity<List<BoxDTO>> findAvailable() {
        return ResponseEntity.ok(boxService.findAvailable());
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam(required = false) Integer id) {
        if (id != null) {
            boxService.deleteById(id);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{id}/clean")
    public ResponseEntity<Void> clean(@PathVariable Integer id) {
        boxService.clean(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/tasks")
    public List<TaskDTO> getTasksForBox(@PathVariable Integer id) {
        return boxService.getTasksForBox(id);
    }
}
