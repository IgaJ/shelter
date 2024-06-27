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
    public List<BoxDTO> findAll() {
        return boxService.listBoxes();
    }

    @GetMapping("/{id}")
    public BoxDTO getBoxById(@PathVariable("id") Integer id) {
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

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam(required = false) Integer id, @RequestParam(required = false) Integer number) {
        if (id != null) {
            boxService.deleteById(id);
        } else if (number != null) {
            boxService.deleteByNumber(number);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
