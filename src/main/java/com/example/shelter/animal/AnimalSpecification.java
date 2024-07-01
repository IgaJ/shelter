package com.example.shelter.animal;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;


public interface AnimalSpecification {

    static Specification<Animal> getAnimalsByCriteria(Integer id, AnimalSpecies species, String name, String sex,
                                                      String size, Integer age, Boolean adopted, Boolean vaccinated) {
        return (Root<Animal> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (id != null) {
                predicates.add(builder.equal(root.get("id"), id));
            }
            if (species != null) {
                predicates.add(builder.equal(root.get("species"), species));
            }
            if (name != null && !name.isEmpty()) {
                predicates.add(builder.equal(root.get("name"), name));
            }
            if (sex != null && !sex.isEmpty()) {
                predicates.add(builder.equal(root.get("sex"), sex));
            }
            if (size != null && !size.isEmpty()) {
                predicates.add(builder.equal(root.get("size"), size));
            }
            if (age != null) {
                predicates.add(builder.equal(root.get("age"), age));
            }
            if (adopted != null) {
                predicates.add(builder.equal(root.get("adopted"), adopted));
            }
            if (vaccinated != null) {
                predicates.add(builder.equal(root.get("vaccinated"), vaccinated));
            }
            return builder.and(predicates.toArray(predicates.toArray(new Predicate[0])));
        };
    }
}
