package com.mftplus.ecommerce.service.impl;

import com.mftplus.ecommerce.api.dto.LoginBody;
import com.mftplus.ecommerce.api.dto.PasswordResetBody;
import com.mftplus.ecommerce.api.dto.RegistrationBody;
import com.mftplus.ecommerce.exception.*;
import com.mftplus.ecommerce.model.entity.User;
import com.mftplus.ecommerce.model.entity.VerificationToken;
import com.mftplus.ecommerce.repository.UserRepository;
import com.mftplus.ecommerce.repository.VerificationTokenRepository;
import com.mftplus.ecommerce.service.EmailService;
import com.mftplus.ecommerce.service.EncryptionService;
import com.mftplus.ecommerce.service.JWTService;
import com.mftplus.ecommerce.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final EncryptionService encryptionService;
    private final JWTService jwtService;
    private final EmailService emailService;
    private final VerificationTokenRepository verificationTokenRepository;

    public UserServiceImpl(UserRepository userRepository, EncryptionService encryptionService, JWTService jwtService, EmailService emailService, VerificationTokenRepository verificationTokenRepository) {
        this.userRepository = userRepository;
        this.encryptionService = encryptionService;
        this.jwtService = jwtService;
        this.emailService = emailService;
        this.verificationTokenRepository = verificationTokenRepository;
    }

//todo : default user role
    @Override
    public User save(RegistrationBody registrationBody) throws UserAlreadyExistsException, EmailFailureException {
        if (userRepository.findByEmailIgnoreCaseAndDeletedFalse(registrationBody.getEmail()).isPresent()
        || userRepository.findByUsernameIgnoreCaseAndDeletedFalse(registrationBody.getUsername()).isPresent()){
            throw new UserAlreadyExistsException();
        }
        User user = new User();
        user.setUsername(registrationBody.getUsername());
        user.setPassword(encryptionService.encryptPassword(registrationBody.getPassword()));
        user.setEmail(registrationBody.getEmail());
        user.setFirstName(registrationBody.getFirstName());
        user.setLastName(registrationBody.getLastName());
        user.setPhoneNumber(registrationBody.getPhoneNumber());

        VerificationToken verificationToken = createVerificationToken(user);
        emailService.sendVerificationEmail(verificationToken);

        return userRepository.save(user);
    }

    @Override
    public void update(User user) throws NoContentException {
        userRepository.findByIdAndDeletedFalse(user.getId()).orElseThrow(
                () -> new NoContentException("No Active User Found with id : " + user.getId())
        );
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void logicalRemove(Long id) throws NoContentException {
        userRepository.findByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No Active User Found with id : " + id)
        );
        userRepository.logicalRemove(id);
    }

    @Override
    public void remove(Long id) throws NoContentException {
        userRepository.findById(id).orElseThrow(
                () -> new NoContentException("No User Found with id : " + id)
        );
        userRepository.deleteById(id);
    }

    @Override
    public User findById(Long id) throws NoContentException {
        return userRepository.findById(id).orElseThrow(
                () -> new NoContentException("No User Found with id : " + id)
        );
    }

    @Override
    public User findByIdAndDeletedFalse(Long id) throws NoContentException {
        return userRepository.findByIdAndDeletedFalse(id).orElseThrow(
                () -> new NoContentException("No Active User Found with id : " + id)
        );
    }

    @Override
    public User findByUsernameIgnoreCaseAndDeletedFalse(String username) throws NoContentException {
        return userRepository.findByUsernameIgnoreCaseAndDeletedFalse(username).orElseThrow(
                () -> new NoContentException("No Active User Found with username : " + username)
        );
    }

    @Override
    public User findByEmailIgnoreCaseAndDeletedFalse(String email) throws NoContentException {
        return userRepository.findByEmailIgnoreCaseAndDeletedFalse(email).orElseThrow(
                () -> new NoContentException("No Active User Found with email : " + email)
        );
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findAllByDeletedFalse() {
        return userRepository.findAllByDeletedFalse();
    }


    private VerificationToken createVerificationToken(User user){
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(jwtService.generateVerificationJwt(user));
        verificationToken.setCreatedTimeStamp(new Timestamp(System.currentTimeMillis()));
        verificationToken.setUser(user);

        user.getVerificationTokens().add(verificationToken);
        return verificationToken;
    }

    public String loginUser(LoginBody loginBody) throws UserNotVerifiedException, EmailFailureException {
        Optional<User> optionalUser = userRepository.findByUsernameIgnoreCaseAndDeletedFalse(loginBody.getUsername());
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            if (encryptionService.verifyPassword(loginBody.getPassword(), user.getPassword())){
                //is user verified?
                if (user.getEmailVerified()) {
                    return jwtService.generateJWT(user);
                } else {
                    List<VerificationToken> verificationTokens = user.getVerificationTokens();
                    boolean resend = verificationTokens.size() == 0 ||
                            verificationTokens.get(0).getCreatedTimeStamp().before(new Timestamp(System.currentTimeMillis() - (60 * 60 * 1000)));
                    if (resend){
                        VerificationToken verificationToken = createVerificationToken(user);
                        verificationTokenRepository.save(verificationToken);
                        emailService.sendVerificationEmail(verificationToken);
                    }

                    throw new UserNotVerifiedException(resend);
                }
            }
        }
        return null;
    }

    //because we are changing data not querying
    @Transactional
    public boolean verifyUser(String token){
        Optional<VerificationToken> optionalVerificationToken = verificationTokenRepository.findByToken(token);

        if (optionalVerificationToken.isPresent()){
            VerificationToken verificationToken = optionalVerificationToken.get();
            User user = verificationToken.getUser();

            if(!user.getEmailVerified()){
                user.setEmailVerified(true);
                userRepository.save(user);
                verificationTokenRepository.deleteByUser(user);
                return true;
            }
        }
        return false;
    }

    public void forgotPassword(String email) throws EmailNotFoundException, EmailFailureException {
        Optional<User> optionalUser = userRepository.findByEmailIgnoreCaseAndDeletedFalse(email);

        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            String token = jwtService.generatePasswordResetJwt(user);
            emailService.sendPasswordResetEmail(user, token);

        } else {
            throw new EmailNotFoundException();
        }
    }

    public void resetPassword(PasswordResetBody body){
        String email = jwtService.getResetPasswordEmail(body.getToken());

        Optional<User> optionalUser = userRepository.findByEmailIgnoreCaseAndDeletedFalse(email);

        if (optionalUser.isPresent()){
            User user = optionalUser.get();

            user.setPassword(encryptionService.encryptPassword(body.getPassword()));
            userRepository.save(user);
        }
    }

    public boolean userHasPermissionToUser(User user, Long id){
        return user.getId() == id;
    }
}
