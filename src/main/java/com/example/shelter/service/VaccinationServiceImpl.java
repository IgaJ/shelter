package com.example.shelter.service;

import com.example.shelter.repository.VaccinationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VaccinationServiceImpl implements VaccinationService {

    private final VaccinationRepository vaccinationRepository;



}
