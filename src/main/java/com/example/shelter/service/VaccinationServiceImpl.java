package com.example.shelter.service;

import com.example.shelter.dto.VaccinationDTO;
import com.example.shelter.entity.Vaccination;
import com.example.shelter.mappers.VaccinationMapper;
import com.example.shelter.repository.VaccinationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VaccinationServiceImpl implements VaccinationService {

    private final VaccinationRepository vaccinationRepository;

    private final VaccinationMapper vaccinationMapper;

    public VaccinationDTO saveNewVaccination(VaccinationDTO vaccinationDTO, Integer id) {
        Vaccination newVaccination = new Vaccination();
        newVaccination.setId(vaccinationDTO.getId());
        newVaccination.setVaccinationDate(LocalDate.now());
        newVaccination.getAnimal().setVaccinated(true);
        return vaccinationMapper.toVaccinationDTO(vaccinationRepository.save(newVaccination));
    }
    public List<VaccinationDTO> listVaccinations() {
        return vaccinationRepository.findAll()
                .stream()
                .map(vaccination -> vaccinationMapper.toVaccinationDTO(vaccination))
                .collect(Collectors.toList());
    }





}
