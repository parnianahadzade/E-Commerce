package com.mftplus.ecommerce.service;

import com.mftplus.ecommerce.model.entity.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    void save(Person person) throws Exception;
    void edit(Person person) throws Exception;
    void logicalRemove(Long id) throws Exception;

    List<Person> findAll() throws Exception;
    List<Person> findAllByDeletedFalse() throws Exception;

    Optional<Person> findById(Long id) throws Exception;

}
