package com.example.shelter.controller;

import com.example.shelter.dto.BoxDTO;
import com.example.shelter.service.BoxService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
}
