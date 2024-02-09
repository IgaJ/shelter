package com.example.shelter.controller;

import com.example.shelter.dto.ActionDTO;
import com.example.shelter.service.ActionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class ActionController {
    private final ActionService actionService;

    @PostMapping("/animals/{id}/actions") // akcja jest podzasobem zwierzęcia
    public ResponseEntity<ActionDTO> saveNewActionToAnimal(@RequestBody ActionDTO actionDTO, @PathVariable Integer id) {
        ActionDTO savedAction = actionService.saveNewActionForAnimal(actionDTO);
        return new ResponseEntity<>(savedAction, HttpStatus.OK);
    }

    @PostMapping("/boxes/{id}/actions")
    public ResponseEntity<ActionDTO> saveNewActionToBox(@RequestBody ActionDTO actionDTO, @PathVariable Integer id) {
        ActionDTO savedAction = actionService.saveNewActionForBox(actionDTO);
        return new ResponseEntity<>(savedAction, HttpStatus.OK);
    }

    @GetMapping("/animals/{id}/actions")
    public List<ActionDTO> getActionsByAnimalId(@PathVariable("id") Integer id) {
        return actionService.getActionsByAnimalId(id);
    }

    @GetMapping("/actions")
    public List<ActionDTO> getAllActions() {
        return actionService.listActions();
    }


}
