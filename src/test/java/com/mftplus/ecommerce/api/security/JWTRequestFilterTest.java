package com.mftplus.ecommerce.api.security;

import com.mftplus.ecommerce.model.entity.User;
import com.mftplus.ecommerce.repository.UserRepository;
import com.mftplus.ecommerce.service.JWTService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class JWTRequestFilterTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private UserRepository userRepository;

    private static final String AUTHENTICATED_PATH = "/auth/me";

    @Test
    public void testUnauthenticatedRequest() throws Exception {
        mvc.perform(get(AUTHENTICATED_PATH)).andExpect(status().is(HttpStatus.FORBIDDEN.value()));
    }

    @Test
    public void testBadToken() throws Exception{
        mvc.perform(get(AUTHENTICATED_PATH)
                .header("Authorization", "BadTokenThatIsNotValid"))
                .andExpect(status().is(HttpStatus.FORBIDDEN.value()));

        mvc.perform(get(AUTHENTICATED_PATH)
                        .header("Authorization", "Bearer BadTokenThatIsNotValid"))
                .andExpect(status().is(HttpStatus.FORBIDDEN.value()));
    }

    @Test
    public void testUnVerifiedUser() throws Exception{
        User user =userRepository.findByUsernameIgnoreCase("UserB").get();
        String token = jwtService.generateJWT(user);

        mvc.perform(get(AUTHENTICATED_PATH)
                .header("Authorization", "Bearer " + token))
                .andExpect(status().is(HttpStatus.FORBIDDEN.value()));
    }

    @Test
    public void testSuccessful() throws Exception{
        User user =userRepository.findByUsernameIgnoreCase("UserA").get();
        String token = jwtService.generateJWT(user);

        mvc.perform(get(AUTHENTICATED_PATH)
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().is(HttpStatus.OK.value()));
    }
}
