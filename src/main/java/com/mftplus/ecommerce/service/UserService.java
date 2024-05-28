package com.mftplus.ecommerce.service;

import com.mftplus.ecommerce.api.dto.LoginBody;
import com.mftplus.ecommerce.api.dto.RegistrationBody;
import com.mftplus.ecommerce.exception.UserAlreadyExistsException;
import com.mftplus.ecommerce.model.entity.User;
import com.mftplus.ecommerce.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final EncryptionService encryptionService;
    private final JWTService jwtService;

    public UserService(UserRepository userRepository, EncryptionService encryptionService, JWTService jwtService) {
        this.userRepository = userRepository;
        this.encryptionService = encryptionService;
        this.jwtService = jwtService;
    }

    public User save(RegistrationBody registrationBody) throws UserAlreadyExistsException {
        if (userRepository.findByEmailIgnoreCase(registrationBody.getEmail()).isPresent()
        || userRepository.findByUsernameIgnoreCase(registrationBody.getUsername()).isPresent()){
            throw new UserAlreadyExistsException();
        }
        User user = new User();
        user.setUsername(registrationBody.getUsername());
        user.setPassword(encryptionService.encryptPassword(registrationBody.getPassword()));
        user.setEmail(registrationBody.getEmail());
        user.setFirstName(registrationBody.getFirstName());
        user.setLastName(registrationBody.getLastName());

        return userRepository.save(user);
    }

    public String loginUser(LoginBody loginBody){
        Optional<User> optionalUser = userRepository.findByUsernameIgnoreCase(loginBody.getUsername());
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            if (encryptionService.verifyPassword(loginBody.getPassword(), user.getPassword())){
                return jwtService.generateJWT(user);
            }
        }
        return null;
    }
}
