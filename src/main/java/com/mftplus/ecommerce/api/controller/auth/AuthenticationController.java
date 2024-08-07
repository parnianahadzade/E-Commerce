package com.mftplus.ecommerce.api.controller.auth;

import com.mftplus.ecommerce.api.dto.*;
import com.mftplus.ecommerce.exception.*;
import com.mftplus.ecommerce.exception.component.ApiExceptionComponent;
import com.mftplus.ecommerce.exception.dto.ApiException;
import com.mftplus.ecommerce.exception.dto.ApiExceptionResponse;
import com.mftplus.ecommerce.model.entity.Role;
import com.mftplus.ecommerce.model.entity.User;
import com.mftplus.ecommerce.service.EncryptionService;
import com.mftplus.ecommerce.service.impl.RoleServiceImpl;
import com.mftplus.ecommerce.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

@RestController
@RequestMapping("${apiPrefix}/auth")
@Slf4j
@CrossOrigin
public class AuthenticationController {

    private final EncryptionService encryptionService;

    private final UserServiceImpl userService;

    private final RoleServiceImpl roleService;


    public AuthenticationController(EncryptionService encryptionService, UserServiceImpl userService, RoleServiceImpl roleService) {
        this.encryptionService = encryptionService;
        this.userService = userService;
        this.roleService = roleService;
    }


    //user registration
    @PostMapping("/register")
    public ResponseEntity registerUser(@Valid @RequestBody RegistrationDTO registrationDTO, BindingResult result) throws DuplicateException, EmailFailureException, NoContentException {

        //validating inputs
        ResponseEntity<ApiExceptionResponse> responseEntity = ApiExceptionComponent.handleValidationErrors(result);
            if (responseEntity != null) {
                return responseEntity;
            }

            //check if passwords match
            if (!Objects.equals(registrationDTO.getPassword(), registrationDTO.getConfirmPassword())) {
                throw new ValidationException("رمز عبور با تکرار آن برابر نیست.");
            }

            //create user
            User user = new User();
            user.setUsername(registrationDTO.getUsername());
            user.setPassword(encryptionService.encryptPassword(registrationDTO.getPassword()));
            user.setEmail(registrationDTO.getEmail());

            //set default user role for new users
            List<Role> roles = Collections.singletonList(roleService.findByIdAndDeletedFalse(1L));
            user.setRoles(roles);

            //save user
            userService.save(user);

            return ResponseEntity.ok().build();

    }

    @PostMapping("/login")
    public ResponseEntity loginUser(@Valid @RequestBody LoginDTO loginDTO, BindingResult result) throws UserAccessDeniedException {

        //validating inputs
        ResponseEntity<ApiExceptionResponse> responseEntity = ApiExceptionComponent.handleValidationErrors(result);
        if (responseEntity != null) {
            return responseEntity;
        }

        String jwt = null;

        try {
            jwt = userService.loginUser(loginDTO);


        } catch (UserNotVerifiedException exception) {
            ApiException apiException = new ApiException();
            apiException.setJwt(null);
            apiException.setField(null);
            apiException.setHttpStatus(HttpStatus.FORBIDDEN);
            apiException.setTime(ZonedDateTime.now(ZoneId.of("Z")));
            apiException.setJwt(null);

            String message = "اکانت کاربر فعال نشده ";
            if (exception.isNewEmailSent()){
                message += "ایمیل فعال سازی مجددا ارسال شد";
            }

            apiException.setMessage(message);

            ApiExceptionResponse apiExceptionResponse = new ApiExceptionResponse(Collections.singletonList(apiException));
            return new ResponseEntity<>(apiExceptionResponse, HttpStatus.FORBIDDEN);

        } catch (EmailFailureException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }


        if (jwt == null){
            throw new UserAccessDeniedException("نام کاربری یا رمز عبور اشتباه است.");
        }else {
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setJwt(jwt);
            loginResponse.setSuccess(true);

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
