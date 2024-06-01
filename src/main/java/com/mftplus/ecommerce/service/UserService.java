package com.mftplus.ecommerce.service;

import com.mftplus.ecommerce.api.dto.LoginBody;
import com.mftplus.ecommerce.api.dto.RegistrationBody;
import com.mftplus.ecommerce.exception.EmailFailureException;
import com.mftplus.ecommerce.exception.UserAlreadyExistsException;
import com.mftplus.ecommerce.model.entity.User;
import com.mftplus.ecommerce.model.entity.VerificationToken;
import com.mftplus.ecommerce.repository.UserRepository;
import com.mftplus.ecommerce.repository.VerificationTokenRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final EncryptionService encryptionService;
    private final JWTService jwtService;
    private final EmailService emailService;
    private final VerificationTokenRepository verificationTokenRepository;

    public UserService(UserRepository userRepository, EncryptionService encryptionService, JWTService jwtService, EmailService emailService, VerificationTokenRepository verificationTokenRepository) {
        this.userRepository = userRepository;
        this.encryptionService = encryptionService;
        this.jwtService = jwtService;
        this.emailService = emailService;
        this.verificationTokenRepository = verificationTokenRepository;
    }

    public User save(RegistrationBody registrationBody) throws UserAlreadyExistsException, EmailFailureException {
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

        VerificationToken verificationToken = createVerificationToken(user);
        emailService.sendVerificationEmail(verificationToken);

        return userRepository.save(user);
    }

    private VerificationToken createVerificationToken(User user){
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(jwtService.generateVerificationJwt(user));
        verificationToken.setCreatedTimeStamp(new Timestamp(System.currentTimeMillis()));
        verificationToken.setUser(user);

        user.getVerificationTokens().add(verificationToken);
        return verificationToken;
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
