package com.example.shelter.repository;

import com.example.shelter.entity.Walk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WalkRepository extends JpaRepository <Walk, UUID> {
}
