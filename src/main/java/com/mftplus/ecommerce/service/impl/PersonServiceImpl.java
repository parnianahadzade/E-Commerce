package com.mftplus.ecommerce.service.impl;

import com.mftplus.ecommerce.exception.NoContentException;
import com.mftplus.ecommerce.model.entity.Person;
import com.mftplus.ecommerce.model.entity.Role;
import com.mftplus.ecommerce.model.entity.User;
import com.mftplus.ecommerce.repository.PersonRepository;
import com.mftplus.ecommerce.service.PersonService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person update(Person person) throws NoContentException {
        Person existingPerson = personRepository.findByIdAndDeletedFalse(person.getId()).orElseThrow(
                () -> new NoContentException("پروفایل مورد نظر یافت نشد.")
        );
        existingPerson.setFirstName(person.getFirstName());
        existingPerson.setLastName(person.getLastName());
        existingPerson.setPhoneNumber(person.getPhoneNumber());
        existingPerson.setAddressLine(person.getAddressLine());
        existingPerson.setPostalCode(person.getPostalCode());

        return personRepository.save(existingPerson);
    }

    @Transactional
    @Override
    public void logicalRemove(Long id) throws NoContentException {
        personRepository.findByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("پروفایل مورد نظر یافت نشد.")
        );
        personRepository.logicalRemove(id);
    }

    @Override
    public void remove(Long id) throws NoContentException {
        personRepository.findById(id).orElseThrow(
                () -> new NoContentException("پروفایل مورد نظر یافت نشد.")
        );
        personRepository.deleteById(id);
    }

    @Override
    public Person findById(Long id) throws NoContentException {
        return personRepository.findById(id).orElseThrow(
                () -> new NoContentException("پروفایل مورد نظر یافت نشد.")
        );
    }

    @Override
    public Person findByIdAndDeletedFalse(Long id) throws NoContentException {
        return personRepository.findByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("پروفایل مورد نظر یافت نشد.")
        );
    }

    @Override
    public Person findByUserUsernameAndDeletedFalse(String username) throws NoContentException {
        return personRepository.findByUserUsernameAndDeletedFalse(username).orElseThrow(
                () -> new NoContentException("پروفایل مورد نظر یافت نشد.")
        );
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public List<Person> findAllByDeletedFalse() {
        return personRepository.findAllByDeletedFalse();
    }

    @Override
    public boolean userHasPermissionToPerson(User user, Long id) {
        // Check if the user has an admin role
        for (Role role : user.getRoles()) {
            if (role.getId().equals(2L)) {
                return true; // Admin users have access to all orders
            }
        }

        // Check if the user ID matches the order ID
        if (Objects.equals(user.getId(), id)) {
            return true;
        }

        return false;
    }
}
