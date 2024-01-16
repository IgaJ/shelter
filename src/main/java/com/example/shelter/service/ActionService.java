package com.example.shelter.service;

import com.example.shelter.dto.ActionDTO;

import java.util.UUID;

public interface ActionService {
    ActionDTO saveNewActionForAnimal(ActionDTO actionDTO);
    ActionDTO saveNewActionForBox(ActionDTO actionDTO);
}
