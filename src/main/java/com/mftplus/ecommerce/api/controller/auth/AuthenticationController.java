package com.mftplus.ecommerce.api.controller.auth;

import com.mftplus.ecommerce.api.dto.*;
import com.mftplus.ecommerce.exception.*;
import com.mftplus.ecommerce.exception.component.ApiExceptionComponent;
import com.mftplus.ecommerce.exception.dto.ApiExceptionResponse;
import com.mftplus.ecommerce.model.entity.Role;
import com.mftplus.ecommerce.model.entity.User;
import com.mftplus.ecommerce.service.EncryptionService;
import com.mftplus.ecommerce.service.impl.RoleServiceImpl;
import com.mftplus.ecommerce.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("${apiPrefix}/auth")
@Slf4j
@CrossOrigin
public class AuthenticationController {

    private final EncryptionService encryptionService;

    private final UserServiceImpl userService;

    private final RoleServiceImpl roleService;

    private final MessageSource messageSource;


    public AuthenticationController(EncryptionService encryptionService, UserServiceImpl userService, RoleServiceImpl roleService, MessageSource messageSource) {
        this.encryptionService = encryptionService;
        this.userService = userService;
        this.roleService = roleService;
        this.messageSource = messageSource;
    }


    @PostMapping("/register")
    public ResponseEntity registerUser(@Valid @RequestBody RegistrationDTO registrationDTO, BindingResult result) throws DuplicateException, EmailFailureException, NoContentException {

        ResponseEntity<ApiExceptionResponse> responseEntity = ApiExceptionComponent.handleValidationErrors(result);
            if (responseEntity != null) {
                return responseEntity;
            }

            if (!Objects.equals(registrationDTO.getPassword(), registrationDTO.getConfirmPassword())) {
                throw new ValidationException("Passwords do not match.");
            }

            User user = new User();
            user.setUsername(registrationDTO.getUsername());
            user.setPassword(encryptionService.encryptPassword(registrationDTO.getPassword()));
            user.setEmail(registrationDTO.getEmail());

            List<Role> roles = Collections.singletonList(roleService.findByIdAndDeletedFalse(1L));
            user.setRoles(roles);
            userService.save(user);
            return ResponseEntity.ok().build();

    }

    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@Valid @RequestBody LoginDTO loginDTO, BindingResult result){
//        if (result.hasErrors()) {
//
//            List<InputFieldError> fieldErrorList = result.getFieldErrors().stream()
//                    .map(error -> new InputFieldError(error.getField(), error.getDefaultMessage()))
//                    .collect(Collectors.toList());
//
//            ValidationResponse validationResponse = new ValidationResponse(fieldErrorList);
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationResponse);
//
//        }
        String jwt = null;

        try {
            jwt = userService.loginUser(loginDTO);


        } catch (UserNotVerifiedException exception) {
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setSuccess(false);
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
            loginResponse.setSuccess(true);

//            httpSession.setAttribute("jwt","Bearer " +jwt);

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
    public ResponseEntity resetPassword(@Valid @RequestBody PasswordResetDTO passwordResetDTO){
        userService.resetPassword(passwordResetDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<User> updateUserInfo(@Valid @RequestBody UserDataChange userDataChange
            , @AuthenticationPrincipal User authUser, @PathVariable Long userId) throws NoContentException {

        if (!userService.userHasPermissionToUser(authUser, userId)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        User user = userService.findById(userId);
        user.setUsername(userDataChange.getUsername());

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
