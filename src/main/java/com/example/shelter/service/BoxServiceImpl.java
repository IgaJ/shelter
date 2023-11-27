package com.example.shelter.service;

import com.example.shelter.dto.BoxDTO;
import com.example.shelter.mappers.BoxMapper;
import com.example.shelter.repository.BoxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoxServiceImpl implements BoxService {

    private final BoxRepository boxRepository;
    private final BoxMapper boxMapper;

    @Override
    public BoxDTO getBoxById(UUID id) {
        return boxMapper.toBoxDTO(boxRepository.findById(id).orElseThrow());
    }

    @Override
    public BoxDTO getBoxByAnimalId(UUID animalId) {
        return boxMapper.toBoxDTO(boxRepository.findBoxByAnimalId(animalId));
    }

    @Override
    public List<BoxDTO> listBoxes() {
        return boxRepository.findAll()
                .stream()
                .map(box -> boxMapper.toBoxDTO(box))
                .collect(Collectors.toList());
    }
}
