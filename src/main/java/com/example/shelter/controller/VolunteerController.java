package com.example.shelter.controller;

import com.example.shelter.service.VolunteerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/volunteers")
public class VolunteerController {

    private final VolunteerService volunteerService;
}
