package com.mftplus.ecommerce.service;

import com.mftplus.ecommerce.api.dto.LoginBody;
import com.mftplus.ecommerce.api.dto.PasswordResetBody;
import com.mftplus.ecommerce.api.dto.RegistrationBody;
import com.mftplus.ecommerce.exception.EmailFailureException;
import com.mftplus.ecommerce.exception.EmailNotFoundException;
import com.mftplus.ecommerce.exception.UserAlreadyExistsException;
import com.mftplus.ecommerce.exception.UserNotVerifiedException;
import com.mftplus.ecommerce.model.entity.User;
import com.mftplus.ecommerce.model.entity.VerificationToken;
import com.mftplus.ecommerce.repository.UserRepository;
import com.mftplus.ecommerce.repository.VerificationTokenRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
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

    public String loginUser(LoginBody loginBody) throws UserNotVerifiedException, EmailFailureException {
        Optional<User> optionalUser = userRepository.findByUsernameIgnoreCase(loginBody.getUsername());
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
        Optional<User> optionalUser = userRepository.findByEmailIgnoreCase(email);

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

        Optional<User> optionalUser = userRepository.findByEmailIgnoreCase(email);

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
