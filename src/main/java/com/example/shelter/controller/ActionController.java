package com.example.shelter.controller;

import com.example.shelter.dto.ActionDTO;
import com.example.shelter.service.ActionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping()
public class ActionController {
    private final ActionService actionService;

    @PostMapping("/animals/{id}/actions") // akcja jest podzasobem zwierzęcia
    public ResponseEntity<ActionDTO> saveNewActionToAnimal(@RequestBody ActionDTO actionDTO, @PathVariable UUID id) {
        ActionDTO savedAction = actionService.saveNewActionForAnimal(actionDTO);
        return new ResponseEntity<>(savedAction, HttpStatus.OK);
    }
    @PostMapping("/boxes/{id}/actions")
    public ResponseEntity<ActionDTO> saveNewActionToBox(@RequestBody ActionDTO actionDTO, @PathVariable UUID id) {
        ActionDTO savedAction = actionService.saveNewActionForBox(actionDTO);
        return new ResponseEntity<>(savedAction, HttpStatus.OK);
    }

}
