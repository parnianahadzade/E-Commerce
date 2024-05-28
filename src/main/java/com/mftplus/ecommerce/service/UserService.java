package com.mftplus.ecommerce.service;

import com.mftplus.ecommerce.api.dto.RegistrationBody;
import com.mftplus.ecommerce.exception.UserAlreadyExistsException;
import com.mftplus.ecommerce.model.entity.User;
import com.mftplus.ecommerce.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(RegistrationBody registrationBody) throws UserAlreadyExistsException {
        if (userRepository.findByEmailIgnoreCase(registrationBody.getEmail()).isPresent()
        || userRepository.findByUsernameIgnoreCase(registrationBody.getUsername()).isPresent()){
            throw new UserAlreadyExistsException();
        }
        User user = new User();
        user.setUsername(registrationBody.getUsername());
        //todo
        user.setPassword(registrationBody.getPassword());
        user.setEmail(registrationBody.getEmail());
        user.setFirstName(registrationBody.getFirstName());
        user.setLastName(registrationBody.getLastName());

        return userRepository.save(user);
    }
}
