package com.example.shelter.repository;

import com.example.shelter.entity.Box;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    @Query("SELECT b FROM Box b WHERE SIZE(b.animals) < :maxSize AND b.boxNumber = :boxNumber")
    Box findBoxWithSizeLessThanAndBoxNumber(@Param("maxSize") int maxSize, @Param("boxNumber") Integer boxNumber);

    @Query("SELECT COUNT(*) FROM Box b WHERE b.isQuarantine = :isQuarantine")
    Optional<Integer> findBoxesWithQuarantine(@Param("isQuarantine") Boolean isQuarantine);

    @Query("SELECT COUNT(*) FROM Box b")
    int countAllBoxes();

    @Query("SELECT b FROM Box b WHERE b.boxNumber = :boxNumber")
    Optional<Box> findByNumber(@Param("boxNumber") Integer boxNumber);

    @Modifying // dodana @modifying bo z @query hibernate oczekuje zapytania select nie delete
    @Query("DELETE FROM Box b WHERE b.boxNumber = :boxNumber")
    void deleteByNumber(@Param("boxNumber") Integer boxNumber);

    @Query("SELECT COALESCE(MAX(b.boxNumber), 0) FROM Box b") // coalesce do obsługi przypadku gdy niema wartości max
    int giveHighestBoxNumber();
}
