package com.mftplus.ecommerce.api.controller.auth;

import com.mftplus.ecommerce.api.dto.*;
import com.mftplus.ecommerce.exception.*;
import com.mftplus.ecommerce.model.entity.User;
import com.mftplus.ecommerce.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final UserServiceImpl userService;

    public AuthenticationController(UserServiceImpl userService) {
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
        String jwt = null;

        try {
            jwt = userService.loginUser(loginBody);


        } catch (UserNotVerifiedException exception) {
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setSuccsess(false);
            String reason = "USER_NOT_VERIFIED";
            if (exception.isNewEmailSent()){
                reason += "_EMAIL_RESENT";
            }
            loginResponse.setFailureReason(reason);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(loginResponse);


        } catch (EmailFailureException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }


        if (jwt == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }else {
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setJwt(jwt);
            loginResponse.setSuccsess(true);
            return ResponseEntity.ok(loginResponse);
        }
    }

    @PostMapping("/verify")
    public ResponseEntity verifyEmail(@RequestParam String token){
        if (userService.verifyUser(token)){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping("/me")
    public User getLoggedInUserProfile(@AuthenticationPrincipal User user){
        return user;
    }

    @PostMapping("/forgot")
    public ResponseEntity forgotPassword(@RequestParam String email){
        try {
            userService.forgotPassword(email);
            return ResponseEntity.ok().build();

        } catch (EmailNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        } catch (EmailFailureException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/reset")
    public ResponseEntity resetPassword(@Valid @RequestBody PasswordResetBody body){
        userService.resetPassword(body);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<User> updateUserInfo(@Valid @RequestBody UserDataChange userDataChange
            , @AuthenticationPrincipal User authUser, @PathVariable Long userId) throws NoContentException {

        if (!userService.userHasPermissionToUser(authUser, userId)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        User user = userService.findById(userId);
        user.setFirstName(userDataChange.getFirstName());
        user.setLastName(userDataChange.getLastName());
        user.setUsername(userDataChange.getUsername());
        user.setPhoneNumber(userDataChange.getPhoneNumber());

        userService.update(user);
        return ResponseEntity.ok().build();
    }

    //todo : should user have access to this? is it admin only?
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<User> logicalRemoveUser(@PathVariable Long userId
            , @AuthenticationPrincipal User authUser) throws NoContentException {

        if (!userService.userHasPermissionToUser(authUser, userId)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        User user = userService.findById(userId);
        user.setEmailVerified(false);
        userService.update(user);

        userService.logicalRemove(userId);
        return ResponseEntity.ok().build();

    }

}
