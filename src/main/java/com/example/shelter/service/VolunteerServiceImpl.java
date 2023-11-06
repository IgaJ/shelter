package com.example.shelter.service;

import com.example.shelter.dto.VolunteerDTO;
import com.example.shelter.mappers.VolunteerMapper;
import com.example.shelter.repository.VolunteerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VolunteerServiceImpl implements VolunteerService {

    private final VolunteerRepository volunteerRepository;
    private final VolunteerMapper volunteerMapper;

    @Override
    public VolunteerDTO saveNewVolunteer(VolunteerDTO volunteer) {
        return volunteerMapper.volunteerToVolunteerDTO(volunteerRepository.save(volunteerMapper.volunteerDTOToVolunteer(volunteer)));
    }

    @Override
    public List<VolunteerDTO> listVolunteers() {
        return volunteerRepository.findAll()
                .stream()
                .map(volunteer -> volunteerMapper.volunteerToVolunteerDTO(volunteer))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<VolunteerDTO> getVolunteerById(UUID id) {
        return Optional.ofNullable(volunteerMapper.volunteerToVolunteerDTO(volunteerRepository.findById(id).orElse(null)));
    }

    @Override
    public List<VolunteerDTO> getVolunteerByName(String name) {
        return volunteerRepository.getVolunteerByName(name)
                .stream()
                .map(volunteer -> volunteerMapper.volunteerToVolunteerDTO(volunteer))
                .collect(Collectors.toList());

    }

    @Override
    public void deleteById(UUID id) {
        if(volunteerRepository.existsById(id)) {
            volunteerRepository.deleteById(id);
        }
    }
}
