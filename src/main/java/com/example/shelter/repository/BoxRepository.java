package com.example.shelter.repository;

import com.example.shelter.entity.Animal;
import com.example.shelter.entity.Box;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BoxRepository extends JpaRepository <Box, UUID> {

    @Query("SELECT b FROM Box b JOIN b.animals a WHERE a.id = :animalId") // zapytanie do tabel łączonych
    Box findBoxByAnimalId(@Param("animalId") UUID id);

}
