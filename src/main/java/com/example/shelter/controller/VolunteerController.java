package com.example.shelter.controller;

import com.example.shelter.dto.VolunteerDTO;
import com.example.shelter.service.VolunteerService;
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
@RequestMapping("/volunteers")
public class VolunteerController {

    private final VolunteerService volunteerService;

    @PostMapping
    public ResponseEntity<VolunteerDTO> saveNewVolunteer (@RequestBody VolunteerDTO volunteerDTO) {
        VolunteerDTO savedVolunteer = volunteerService.saveNewVolunteer(volunteerDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/volunteers/id/" + savedVolunteer.getId().toString());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @GetMapping
    public List<VolunteerDTO> listVolunteers() {
        return volunteerService.listVolunteers();
    }

    @GetMapping(params = "name")
    public List<VolunteerDTO> getVolunteerByName (String name) {
        return volunteerService.getVolunteerByName(name);
    }

    @GetMapping("/{id}")
    public VolunteerDTO getVolunteerById(@PathVariable ("id") UUID id) {
        return volunteerService.getVolunteerById(id).orElseThrow(null);
    }

    public ResponseEntity<?> deleteVolunteerById(@PathVariable ("id") UUID id) {
        volunteerService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
