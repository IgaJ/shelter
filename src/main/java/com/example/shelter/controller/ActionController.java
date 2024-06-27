package com.example.shelter.controller;

import com.example.shelter.dto.ActionDTO;
import com.example.shelter.service.ActionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/actions")
public class ActionController {
    private final ActionService actionService;

    @PostMapping("/animal")
    public ResponseEntity<ActionDTO> saveNewAnimalAction(@RequestBody ActionDTO actionDTO) {
        return ResponseEntity.ok(actionService.saveNewActionForAnimal(actionDTO));
    }

    @PostMapping("/box")
    public ResponseEntity<ActionDTO> saveNewBoxAction(@RequestBody ActionDTO actionDTO) {
        return ResponseEntity.ok(actionService.saveNewActionForBox(actionDTO));
    }

    @GetMapping("/animal/{id}")
    public List<ActionDTO> getByAnimalId(@PathVariable("id") Integer id) {
        return actionService.getActionsByAnimalId(id);
    }

    @GetMapping
    public List<ActionDTO> findAll() {
        return actionService.listActions();
    }
}
