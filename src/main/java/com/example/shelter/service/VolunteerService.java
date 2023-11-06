package com.example.shelter.service;

import com.example.shelter.dto.AnimalDTO;
import com.example.shelter.dto.VolunteerDTO;
import com.example.shelter.entity.Volunteer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VolunteerService {

    VolunteerDTO saveNewVolunteer (VolunteerDTO volunteer);

    List<VolunteerDTO> listVolunteers();

    Optional<VolunteerDTO> getVolunteerById(UUID id);

    List<VolunteerDTO> getVolunteerByName(String name);

    void deleteById(UUID id);
}
