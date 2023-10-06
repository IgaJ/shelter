package com.example.shelter.repository;

import com.example.shelter.model.AnimalDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AnimalRepository extends JpaRepository <AnimalDTO, UUID> {
}
