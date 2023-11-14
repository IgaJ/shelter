package com.example.shelter.service;

import com.example.shelter.dto.VaccinationDTO;

import java.util.List;

public interface VaccinationService {
    VaccinationDTO saveNewVaccination(VaccinationDTO vaccinationDTO, Integer id);

    List<VaccinationDTO> listVaccinations();
}
