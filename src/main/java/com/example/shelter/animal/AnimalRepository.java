package com.example.shelter.animal;

import com.example.shelter.animal.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AnimalRepository extends JpaRepository <Animal, Integer> {
    @Query("SELECT a FROM Animal a WHERE LOWER(a.name) = LOWER(:name)")
    List<Animal> getAnimalByName(@Param("name") String name);

    @Query("SELECT a FROM Animal a WHERE a.age = :age")
    List<Animal> getAnimalByAge(@Param("age") Integer age);

    @Query("SELECT a FROM Animal a WHERE a.sex = :sex")
    List<Animal> getAnimalBySex(String sex);

    @Query("SELECT a FROM Animal a WHERE a.size = :size")
    List<Animal> getAnimalBySize(String size);

    @Query("SELECT a FROM Animal a WHERE a.vaccinated = :vaccinated") // query z dwoma warunkami?
    List<Animal> getNonVaccinated(Boolean vaccinated);

    @Query("SELECT a FROM Animal a WHERE a.vaccinated = true and a.adopted = false")
    List<Animal> getAvailableForAdoption();

    List<Animal> findAllByVaccinatedAndAdopted(Boolean vaccinated, Boolean adopted);

    @Query("SELECT a FROM Animal a WHERE a.id = :id")
    Optional<Animal> getAnimalById(@Param("id") Integer id);
}
// criteria, dostarczać parametr
// example

