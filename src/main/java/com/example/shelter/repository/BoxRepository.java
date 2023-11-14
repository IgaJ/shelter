package com.example.shelter.repository;

import com.example.shelter.entity.Box;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BoxRepository extends JpaRepository <Box, UUID> {
}
