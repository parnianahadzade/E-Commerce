package com.mftplus.ecommerce.service;

import com.mftplus.ecommerce.controller.exception.UserAlreadyExistsException;
import com.mftplus.ecommerce.model.entity.LocalUser;
import com.mftplus.ecommerce.model.repository.LocalUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final LocalUserRepository localUserRepository;

    public UserServiceImpl(LocalUserRepository localUserRepository) {
        this.localUserRepository = localUserRepository;
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
    public void save(LocalUser user) throws Exception{
        if (localUserRepository.findByEmailIgnoreCase(user.getEmail()).isPresent()
                || localUserRepository.findByUsernameIgnoreCase(user.getUsername()).isPresent()){
            throw new UserAlreadyExistsException();
        }
        localUserRepository.save(user);
    }

    @Override
    public List<LocalUser> findAll() {
        return null;
    }
}
