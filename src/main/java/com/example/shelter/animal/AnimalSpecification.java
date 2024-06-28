package com.example.shelter.animal;

import org.springframework.data.jpa.domain.Specification;

public interface AnimalSpecification {

    static Specification<Animal> hasId (Integer id) {
        return (root, query, builder) -> id == null ? builder.conjunction() : builder.equal(root.get("id"), id);
    }

    static Specification<Animal> hasSpecies (AnimalSpecies species) {
        return (root, query, builder) -> species == null ? builder.conjunction() : builder.equal(root.get("species"), species);
    }

    static Specification<Animal> hasName (String name) {
        return (root, query, builder) -> name == null ? builder.conjunction() : builder.equal(root.get("name"), name);
    }

    static Specification<Animal> hasSex (String sex) {
        return (root, query, builder) -> sex == null ? builder.conjunction() : builder.equal(root.get("sex"), sex);
    }

    static Specification<Animal> hasSize (String size) {
        return (root, query, builder) -> size == null ? builder.conjunction() : builder.equal(root.get("size"), size);
    }

    static Specification<Animal> hasAge (Integer age) {
        return (root, query, builder) -> age == null ? builder.conjunction() : builder.equal(root.get("age"), age);
    }
}
