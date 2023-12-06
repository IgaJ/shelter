package com.example.shelter.controller;

import com.example.shelter.dto.BoxDTO;
import com.example.shelter.entity.Box;
import com.example.shelter.service.BoxService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/boxes")
public class BoxController {
    private final BoxService boxService;

    @GetMapping
    public List<BoxDTO> listBoxes() {
        return boxService.listBoxes();
    }

    @GetMapping("/{id}")
    public BoxDTO getBoxById(@PathVariable("id") UUID id) {
        return boxService.getBoxById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BoxDTO> updateBox(@PathVariable("id") UUID animalId, @RequestBody BoxDTO boxDTO) {
        BoxDTO updatedBox = boxService.changeBox(animalId, boxDTO);
        return new ResponseEntity<>(updatedBox, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") UUID id) {
        boxService.deleteById(id);
        String message = "Deleted: ";
        return new ResponseEntity<>(message + id, HttpStatus.OK);

    }
}
