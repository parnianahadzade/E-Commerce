package com.mftplus.ecommerce.repository;

import com.mftplus.ecommerce.model.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findAllByDeletedFalse();

    Optional<Person> findByIdAndDeletedFalse(Long id);

    Optional<Person> findByUserUsernameAndDeletedFalse(String username);

    @Modifying
    @Query("update personEntity oo set oo.deleted=true where oo.id=:id")
    void logicalRemove(Long id);
}

