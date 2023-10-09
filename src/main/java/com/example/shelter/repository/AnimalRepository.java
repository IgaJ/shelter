package com.example.shelter.repository;

import com.example.shelter.entities.Animal;
import com.example.shelter.model.AnimalDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AnimalRepository extends JpaRepository <Animal, UUID> {
    @Query("SELECT d FROM Animal d WHERE LOWER(d.name) = LOWER(:name)")
    List<Animal> getAnimalByName(@Param("name") String name);

    @Query("SELECT d FROM Animal d WHERE d.age = :age")
    List<Animal> getAnimalByAge(@Param("age") Integer age);

    @Query("SELECT d FROM Animal d WHERE d.vaccinated = :vaccinated")
    List<Animal> getVaccinated(@Param("vaccinated") String vaccinated);

}
