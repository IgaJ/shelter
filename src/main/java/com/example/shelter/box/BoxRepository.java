package com.example.shelter.box;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoxRepository extends JpaRepository <Box, Integer> {
    @Query("SELECT b FROM Box b WHERE SIZE(b.animals) < b.maxAnimals AND (: isQuarantine IS NULL OR b.isQuarantine = :isQuarantine) ORDER BY b.id ASC")
    Optional<Box> findFirstAvailable(@Param("isQuarantine") Boolean isQuarantine);

    @Query("SELECT b FROM Box b WHERE b.boxNumber = :boxNumber AND b.isQuarantine = :isQuarantine")
    Optional<Box> findByBoxNumberAndIsQuarantine(@Param("boxNumber") Integer boxNumber, @Param("isQuarantine") Boolean isQuarantine);

    @Query("SELECT b FROM Box b JOIN b.animals a WHERE a.id = :animalId") // zapytanie do tabel łączonych
    Box findByAnimalId(@Param("animalId") Integer id);

    @Query("SELECT b FROM Box b WHERE SIZE(b.animals) < b.maxAnimals")
    List<Box> findAvailable();

    @Modifying // dodana @modifying bo z @query hibernate oczekuje zapytania select nie delete
    @Query("DELETE FROM Box b WHERE b.boxNumber = :boxNumber")
    void deleteByNumber(@Param("boxNumber") Integer boxNumber);

    @Query("SELECT COALESCE(MAX(b.boxNumber), 0) FROM Box b") // COALESCE - zwraca pierwszą niepustą wartość. Jeśli MAX(b.boxNumber) zwróci null (brak rekordów) to coalesce zwróci 0
    int giveHighestBoxNumber();
}
