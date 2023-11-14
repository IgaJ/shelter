package com.example.shelter.controller;

import com.example.shelter.dto.AnimalDTO;
import com.example.shelter.dto.VaccinationDTO;
import com.example.shelter.service.VaccinationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/vaccinations")
public class VaccinationController {
    private final VaccinationService vaccinationService;

    @PostMapping
    public ResponseEntity<VaccinationDTO> saveNewVaccination (@RequestBody VaccinationDTO vaccinationDTO, @RequestBody Integer id) {
        VaccinationDTO savedVaccination = vaccinationService.saveNewVaccination(vaccinationDTO, id);
        return new ResponseEntity<>(savedVaccination, HttpStatus.OK);
    }

}
