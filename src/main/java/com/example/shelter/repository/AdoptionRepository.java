package com.example.shelter.repository;

import com.example.shelter.entity.Adoption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AdoptionRepository extends JpaRepository <Adoption, UUID> {
}
