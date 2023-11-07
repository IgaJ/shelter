package com.example.shelter.repository;

import com.example.shelter.entity.Box;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BoxRepository extends JpaRepository<Box, UUID> {
}
