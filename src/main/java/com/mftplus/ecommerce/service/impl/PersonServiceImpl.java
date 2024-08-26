package com.mftplus.ecommerce.service.impl;

import com.mftplus.ecommerce.exception.NoContentException;
import com.mftplus.ecommerce.model.entity.Person;
import com.mftplus.ecommerce.model.entity.User;
import com.mftplus.ecommerce.repository.PersonRepository;
import com.mftplus.ecommerce.service.PersonService;
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
                () -> new NoContentException("No Active Person Found with id : " + person.getId())
        );
        existingPerson.setFirstName(person.getFirstName());
        existingPerson.setLastName(person.getLastName());
        existingPerson.setPhoneNumber(person.getPhoneNumber());
        existingPerson.setAddress(person.getAddress());

        return personRepository.save(existingPerson);
    }

    @Override
    public void logicalRemove(Long id) throws NoContentException {
        personRepository.findByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No Active Person Found with id : " + id)
        );
        personRepository.logicalRemove(id);
    }

    @Override
    public void remove(Long id) throws NoContentException {
        personRepository.findById(id).orElseThrow(
                () -> new NoContentException("No Person Found with id : " + id)
        );
        personRepository.deleteById(id);
    }

    @Override
    public Person findById(Long id) throws NoContentException {
        return personRepository.findById(id).orElseThrow(
                () -> new NoContentException("No Person Found with id : " + id)
        );
    }

    @Override
    public Person findByIdAndDeletedFalse(Long id) throws NoContentException {
        return personRepository.findByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No Active Person Found with id : " + id)
        );
    }

    @Override
    public Person findByUserUsernameAndDeletedFalse(String username) throws NoContentException {
        return personRepository.findByUserUsernameAndDeletedFalse(username).orElseThrow(
                () -> new NoContentException("No Active Person Found with username : " + username)
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
        return Objects.equals(user.getPerson().getId(), id);
    }
}
