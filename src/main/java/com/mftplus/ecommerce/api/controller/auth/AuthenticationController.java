package com.mftplus.ecommerce.api.controller.auth;

import com.mftplus.ecommerce.api.dto.LoginBody;
import com.mftplus.ecommerce.api.dto.LoginResponse;
import com.mftplus.ecommerce.api.dto.RegistrationBody;
import com.mftplus.ecommerce.exception.EmailFailureException;
import com.mftplus.ecommerce.exception.UserAlreadyExistsException;
import com.mftplus.ecommerce.model.entity.User;
import com.mftplus.ecommerce.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@Valid @RequestBody RegistrationBody registrationBody){
        try {
            userService.save(registrationBody);
            return ResponseEntity.ok().build();
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }catch (EmailFailureException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginBody loginBody){
        String jwt = userService.loginUser(loginBody);
        if (jwt == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }else {
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setJwt(jwt);
            return ResponseEntity.ok(loginResponse);
        }
    }

    @GetMapping("/me")
    public User getLoggedInUserProfile(@AuthenticationPrincipal User user){
        return user;
    }
}
