package com.mftplus.ecommerce.model.repository;

import com.mftplus.ecommerce.model.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {

    Optional<Person> findByEmailIgnoreCase(String email);

    @Transactional
    @Modifying
    @Query("update personEntity oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);

    List<Person> findAllByDeletedFalse();



}
