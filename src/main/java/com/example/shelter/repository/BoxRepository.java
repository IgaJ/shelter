package com.example.shelter.repository;

import com.example.shelter.entity.Animal;
import com.example.shelter.entity.Box;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BoxRepository extends JpaRepository <Box, UUID> {

    @Query("SELECT d FROM Box d WHERE d.id = :id")
    Box getBoxByAnimalId(UUID id);
}
