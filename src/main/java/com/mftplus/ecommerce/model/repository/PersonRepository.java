package com.mftplus.ecommerce.model.repository;

import com.mftplus.ecommerce.model.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person,Long> {

    Optional<Person> findByEmailIgnoreCase(String email);

}
