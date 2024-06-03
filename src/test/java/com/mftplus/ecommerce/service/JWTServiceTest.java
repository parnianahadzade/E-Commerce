package com.mftplus.ecommerce.service;

import com.mftplus.ecommerce.model.entity.User;
import com.mftplus.ecommerce.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JWTServiceTest {

    @Autowired
    private JWTService jwtService;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testVerificationTokenNotUsableForLogin(){
        User user = userRepository.findByUsernameIgnoreCase("UserA").get();
        String token = jwtService.generateVerificationJwt(user);

        Assertions.assertNull(jwtService.getUsername(token),
                " verification token should not contain token.");

    }

    @Test
    public void testAuthTokenReturnUsername(){
        User user = userRepository.findByUsernameIgnoreCase("UserA").get();
        String token = jwtService.generateJWT(user);

        Assertions.assertEquals(user.getUsername(),
                jwtService.getUsername(token),
                        " token for auth should contain users username.");

    }
}
