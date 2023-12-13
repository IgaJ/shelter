package com.example.shelter.controller;

import com.example.shelter.dto.BoxDTO;
import com.example.shelter.service.BoxService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @GetMapping("/check/{boxNumber}") // chceck if available by boxNumber
    public ResponseEntity<String> checkIfAvailableByBoxNumber(@PathVariable("boxNumber") Integer boxNumber) {
        String message = " available";
        String error = " unavailable";
        BoxDTO available = boxService.findBoxWithSizeLessThanBoxCapacityAndBoxNumber(boxNumber);
        if (available != null) {
            return new ResponseEntity<>(available.getBoxNumber() + message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(boxNumber + error, HttpStatus.valueOf(400));
        }
    }

    @GetMapping("/available")
    public List<BoxDTO> findBoxesWithPlace() {
        return boxService.findBoxesWithPlace();
    }

    @DeleteMapping("/id/{id}") // prefix id dodany żeby uniknąć ambiguos handler methods
    public ResponseEntity<String> deleteById(@PathVariable("id") UUID id) {
        boxService.deleteById(id);
        String message = "Deleted: ";
        return new ResponseEntity<>(message + id, HttpStatus.OK);
    }

    @DeleteMapping("/number/{number}") // prefix number dodany żeby uniknąć ambiguos handler methods
    public ResponseEntity<String> deleteByNumber(@PathVariable("number") Integer number) {
        boxService.deleteByNumber(number);
        String message = "Deleted: ";
        return new ResponseEntity<>(message + number, HttpStatus.OK);
    }
}
