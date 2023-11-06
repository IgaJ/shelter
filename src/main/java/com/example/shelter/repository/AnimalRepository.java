package com.example.shelter.repository;

import com.example.shelter.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
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

    @Query("SELECT d FROM Animal d WHERE d.box = :box")
    List<Animal> getAnimalsByBox(@Param("box") Integer box);

    @Query("SELECT d FROM Animal d WHERE d.adoptionReady = :adoptionReady")
    List<Animal> getAnimalsByAdoption(@Param("adoptionReady") String adoptionReady);
}

