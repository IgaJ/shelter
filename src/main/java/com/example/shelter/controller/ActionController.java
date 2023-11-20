package com.example.shelter.controller;

import com.example.shelter.dto.ActionDTO;
<<<<<<< HEAD
import com.example.shelter.service.ActionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
=======
import com.example.shelter.entity.Action;
import com.example.shelter.service.ActionService;
import lombok.RequiredArgsConstructor;
>>>>>>> origin/master
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

<<<<<<< HEAD
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/actions")
public class ActionController {
    private final ActionService actionService;

    @PostMapping("/animals/{id}/actions") // akcja jest podzasobem zwierzęcia
    public ResponseEntity<ActionDTO> saveNewAction(@RequestBody ActionDTO actionDTO, @PathVariable UUID id) {
        ActionDTO savedAction = actionService.saveNewAction(actionDTO, id);
        return new ResponseEntity<>(savedAction, HttpStatus.OK);
    }
=======
@RequiredArgsConstructor
@RestController
@RequestMapping
public class ActionController {

    private final ActionService actionService;

    @PostMapping("/animals/{id}/actions") // akcja jest podzasobem zwierzęcia
    public ResponseEntity<ActionDTO> saveNewAction (@RequestBody ActionDTO actionDTO, @PathVariable UUID id) {
        ActionDTO savedAction = actionService.saveNewAction(actionDTO, id);
        return new ResponseEntity<>(savedAction, HttpStatus.OK);
    }




>>>>>>> origin/master
}
