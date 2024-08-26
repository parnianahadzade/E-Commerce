package com.mftplus.ecommerce.service;

import com.mftplus.ecommerce.exception.NoContentException;
import com.mftplus.ecommerce.model.entity.Person;
import com.mftplus.ecommerce.model.entity.User;

import java.util.List;

public interface PersonService {

    Person save(Person person);

    Person update(Person person) throws NoContentException;

    void logicalRemove(Long id) throws NoContentException;

    void remove(Long id) throws NoContentException;

    Person findById(Long id) throws NoContentException;

    Person findByIdAndDeletedFalse(Long id) throws NoContentException;

    Person findByUserUsernameAndDeletedFalse(String username) throws NoContentException;

    List<Person> findAll();

    List<Person> findAllByDeletedFalse();

    boolean userHasPermissionToPerson(User user, Long id);
}
