package com.mftplus.ecommerce.service;

import com.mftplus.ecommerce.controller.exception.PersonAlreadyExistsException;
import com.mftplus.ecommerce.model.entity.Person;
import com.mftplus.ecommerce.model.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

//    public void registerUser(RegistrationBody registrationBody) throws UserAlreadyExistsException {
//        if (localUserRepository.findByEmailIgnoreCase(registrationBody.getEmail()).isPresent()
//        || localUserRepository.findByUsernameIgnoreCase(registrationBody.getUsername()).isPresent()){
//            throw new UserAlreadyExistsException();
//        }
//        LocalUser localUser = new LocalUser();
//        localUser.setUsername(registrationBody.getUsername());
//        localUser.setEmail(registrationBody.getEmail());
//        localUser.setFirstName(registrationBody.getFirstName());
//        localUser.setLastName(registrationBody.getLastName());
//        //todo encrypted password
//        localUser.setPassword(registrationBody.getPassword());
//
//        localUserRepository.save(localUser);
//    }

    @Override
    public void save(Person person) throws Exception{
        if (personRepository.findByEmailIgnoreCase(person.getEmail()).isPresent()){
            throw new PersonAlreadyExistsException();
        }
        personRepository.save(person);
    }

    @Override
    public void edit(Person person) throws Exception {
        personRepository.save(person);
    }

    @Override
    public List<Person> findAll() throws Exception{
        return personRepository.findAll();
    }

    @Override
    public Optional<Person> findById(Long id) throws Exception {
        return personRepository.findById(id);
    }

}
