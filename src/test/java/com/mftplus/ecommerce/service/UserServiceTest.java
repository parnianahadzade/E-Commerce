package com.mftplus.ecommerce.service;

import com.icegreen.greenmail.configuration.GreenMailConfiguration;
import com.icegreen.greenmail.junit5.GreenMailExtension;
import com.icegreen.greenmail.util.ServerSetupTest;
import com.mftplus.ecommerce.api.dto.LoginBody;
import com.mftplus.ecommerce.api.dto.PasswordResetBody;
import com.mftplus.ecommerce.exception.EmailFailureException;
import com.mftplus.ecommerce.exception.EmailNotFoundException;
import com.mftplus.ecommerce.exception.DuplicateException;
import com.mftplus.ecommerce.exception.UserNotVerifiedException;
import com.mftplus.ecommerce.model.entity.User;
import com.mftplus.ecommerce.model.entity.VerificationToken;
import com.mftplus.ecommerce.repository.UserRepository;
import com.mftplus.ecommerce.repository.VerificationTokenRepository;
import com.mftplus.ecommerce.service.impl.UserServiceImpl;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

//run with coverage for more info
@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceTest {

    @RegisterExtension
    private static GreenMailExtension greenMailExtension = new GreenMailExtension(ServerSetupTest.SMTP)
            .withConfiguration(GreenMailConfiguration.aConfig().withUser("springboot", "secret"))
            .withPerMethodLifecycle(true);

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EncryptionService encryptionService;

    @Test
    @Transactional
    public void testRegisterUser() throws MessagingException {
        User user = new User();
        user.setUsername("UserA");
        user.setEmail("UserServiceTest$testRegisterUser@junit.com");
        user.setPassword("MySecretPassword123");

        Assertions.assertThrows(DuplicateException.class,
                () -> userService.save(user), " username should already be in use.");

        user.setUsername("UserServiceTestTestRegisterUser");
        user.setEmail("UserA@junit.com");

        Assertions.assertThrows(DuplicateException.class,
                () -> userService.save(user), " email should already be in use.");

        user.setEmail("UserServiceTest$testRegisterUser@junit.com");

        Assertions.assertDoesNotThrow(
                () -> userService.save(user), " user should register successfully");

        Assertions.assertEquals(user.getEmail(),
                greenMailExtension.getReceivedMessages()[0]
                        .getRecipients(Message.RecipientType.TO)[0].toString());
    }


    @Test
    @Transactional
    public void testLoginUser() throws UserNotVerifiedException, EmailFailureException {
        LoginBody body = new LoginBody();

        body.setUsername("UserA-NotExists");
        body.setPassword("PasswordA123");

        Assertions.assertNull(userService.loginUser(body), " The user should not exist.");

        body.setUsername("UserA");
        body.setPassword("BadPassword123");

        Assertions.assertNull(userService.loginUser(body), " The password should be incorrect.");

        body.setPassword("PasswordA123");

        Assertions.assertNotNull(userService.loginUser(body), " The user should login successfully.");

        body.setUsername("UserB");
        body.setPassword("PasswordB123");
        try {
           userService.loginUser(body);
           Assertions.assertTrue(false, " User should not have email verified.");

        }catch (UserNotVerifiedException exception){
            Assertions.assertTrue(exception.isNewEmailSent(), " Email verification should be sent.");
            Assertions.assertEquals(1, greenMailExtension.getReceivedMessages().length);
        }

        try {
            userService.loginUser(body);
            Assertions.assertTrue(false, " User should not have email verified.");

        }catch (UserNotVerifiedException exception){
            Assertions.assertFalse(exception.isNewEmailSent(), " Email verification should be resent.");
            Assertions.assertEquals(1, greenMailExtension.getReceivedMessages().length);
        }
    }


    @Test
    @Transactional
    public void testVerifyUser() throws EmailFailureException {
        Assertions.assertFalse(userService.verifyUser("Bad Token"),
                " Token is bad or does not exist should return false.");

        LoginBody body = new LoginBody();
        body.setUsername("UserB");
        body.setPassword("PasswordB123");

        try {
            userService.loginUser(body);
            Assertions.assertTrue(false, " User should not have email verified.");

        }catch (UserNotVerifiedException exception){
            List<VerificationToken> tokens = verificationTokenRepository.findByUser_IdOrderByIdDesc(2L);
            String token = tokens.get(0).getToken();
            Assertions.assertTrue(userService.verifyUser(token), " Token should be valid.");

            Assertions.assertNotNull(body, " The user should now be verified.");

        }
    }

    @Test
    @Transactional
    public void testForgotPassword() throws MessagingException {
        Assertions.assertThrows(EmailNotFoundException.class,
                () -> userService.forgotPassword("UserNotExists@junit.com"));

        Assertions.assertDoesNotThrow(() -> userService.forgotPassword(
                "UserA@junit.com"), "Non existing email should be rejected.");

        Assertions.assertEquals("UserA@junit.com",
                greenMailExtension.getReceivedMessages()[0]
                        .getRecipients(Message.RecipientType.TO)[0].toString(),
                "Password reset email should be sent.");
    }

    @Test
    @Transactional
    public void testResetPassword(){
        User user = userRepository.findByUsernameIgnoreCaseAndDeletedFalse("UserA").get();

        String token =
                jwtService.generatePasswordResetJwt(user);

        PasswordResetBody body = new PasswordResetBody();
        body.setToken(token);
        body.setPassword("NewPassword1234");

        userService.resetPassword(body);

        user = userRepository.findByUsernameIgnoreCaseAndDeletedFalse("UserA").get();

        Assertions.assertTrue(encryptionService.verifyPassword("NewPassword1234",
                user.getPassword()), "Password change should be written to DB");
    }

}
