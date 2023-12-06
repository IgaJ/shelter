package com.example.shelter.repository;

import com.example.shelter.entity.Animal;
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

    @Query("SELECT d FROM Animal d WHERE d.sex = :sex")
    List<Animal> getAnimalBySex(String sex);

    @Query("SELECT d FROM Animal d WHERE d.size = :size")
    List<Animal> getAnimalBySize(String size);

    @Query("SELECT d FROM Animal d WHERE d.vaccinated = :vaccinated") // query z dwoma warunkami?
    List<Animal> getNonVaccinated(Boolean vaccinated);

    @Query("SELECT d FROM Animal d WHERE d.vaccinated = true and d.adopted = false")
    List<Animal> getAvailableForAdoption();

    List<Animal> findAllByVaccinatedAndAdopted(Boolean vaccinated, Boolean adopted);

    @Query("SELECT d FROM Animal d WHERE d.id = :id")
    Animal getAnimalById(@Param("id") UUID id);
}

