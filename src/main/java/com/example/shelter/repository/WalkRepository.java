package com.example.shelter.repository;

import com.example.shelter.entity.Walk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WalkRepository extends JpaRepository<Walk, UUID> {
}
