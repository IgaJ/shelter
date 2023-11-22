package com.example.shelter.controller;

import com.example.shelter.dto.BoxDTO;
import com.example.shelter.service.BoxService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/boxes")
public class BoxController {
    private final BoxService boxService;

    @GetMapping("/{id}")
    public BoxDTO getBoxById(@PathVariable("id") UUID id) {
        return boxService.getBoxById(id);
    }
}
