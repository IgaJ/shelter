package com.example.shelter.repository;

import com.example.shelter.entity.Box;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BoxRepository extends JpaRepository <Box, UUID> {

    @Query("SELECT b FROM Box b JOIN b.animals a WHERE a.id = :animalId") // zapytanie do tabel łączonych
    Box findBoxByAnimalId(@Param("animalId") UUID id);

    @Query("SELECT b FROM Box b WHERE SIZE(b.animals) < :maxSize AND (: isQuarantine IS NULL OR b.isQuarantine = :isQuarantine)")
    List<Box> findBoxesWithSizeLessThanAndQuarantine(@Param("maxSize") int maxSize, @Param("isQuarantine") Boolean isQuarantine);

    @Query("SELECT COUNT(*) FROM Box b WHERE b.isQuarantine = :isQuarantine")
    int findBoxesWithQuarantine(@Param("isQuarantine") Boolean isQuarantine);

}
