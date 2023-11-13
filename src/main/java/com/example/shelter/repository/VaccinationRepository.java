package com.example.shelter.repository;

import com.example.shelter.entity.Animal;
import com.example.shelter.entity.Vaccination;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface VaccinationRepository extends JpaRepository<Vaccination, UUID> {

}
