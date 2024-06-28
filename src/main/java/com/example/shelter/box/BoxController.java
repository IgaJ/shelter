package com.example.shelter.box;

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
        return ResponseEntity.ok(boxService.listBoxes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoxDTO> getBoxById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(boxService.getBoxById(id));
    }

    @GetMapping("/available")
    public ResponseEntity<List<BoxDTO>> findBoxesWithPlace() {
        return ResponseEntity.ok(boxService.findBoxesWithPlace());
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam(required = false) Integer id) {
        if (id != null) {
            boxService.deleteById(id);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
