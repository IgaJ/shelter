package com.example.shelter.box;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoxRepository extends JpaRepository <Box, Integer> {
    @Query(value = "SELECT b.* FROM Box b LEFT JOIN Animals a ON b.id = a.box_id GROUP BY b.id, b.max_animals, b.is_quarantine HAVING COUNT(a.id) < b.max_animals AND (:isQuarantine IS NULL OR b.is_quarantine = :isQuarantine) ORDER BY b.id ASC LIMIT 1", nativeQuery = true)
    Optional<Box> findFirstAvailable(@Param("isQuarantine") Boolean isQuarantine);

    @Query(value = "SELECT * FROM Box b WHERE b.box_number = :boxNumber AND b.is_quarantine = :isQuarantine", nativeQuery = true)
    Optional<Box> findByBoxNumberAndIsQuarantine(@Param("boxNumber") Integer boxNumber, @Param("isQuarantine") Boolean isQuarantine);

    @Query(value = "SELECT b.* FROM Box b LEFT JOIN Animals a ON b.id = a.box_id GROUP BY b.id HAVING COUNT(a.id) < b.max_animals", nativeQuery = true)
    List<Box> findAvailable();

    @Query(value = "SELECT COALESCE(MAX(box_number), 0) FROM Box", nativeQuery = true)
    int giveHighestBoxNumber();
}
