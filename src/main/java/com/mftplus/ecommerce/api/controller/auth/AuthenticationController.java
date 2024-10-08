package com.mftplus.ecommerce.api.controller.auth;

import com.fasterxml.jackson.annotation.JsonView;
import com.mftplus.ecommerce.api.dto.*;
import com.mftplus.ecommerce.exception.*;
import com.mftplus.ecommerce.exception.component.ApiValidationComponent;
import com.mftplus.ecommerce.exception.dto.ApiOverallError;
import com.mftplus.ecommerce.exception.dto.ApiResponse;
import com.mftplus.ecommerce.model.entity.Role;
import com.mftplus.ecommerce.model.entity.User;
import com.mftplus.ecommerce.model.entity.Views;
import com.mftplus.ecommerce.service.EncryptionService;
import com.mftplus.ecommerce.service.impl.RoleServiceImpl;
import com.mftplus.ecommerce.service.impl.UserServiceImpl;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
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
    //todo

    private final EncryptionService encryptionService;

    private final UserServiceImpl userService;

    private final RoleServiceImpl roleService;

    private final ApiValidationComponent validationComponent;


    public AuthenticationController(EncryptionService encryptionService, UserServiceImpl userService, RoleServiceImpl roleService, ApiValidationComponent validationComponent) {
        this.encryptionService = encryptionService;
        this.userService = userService;
        this.roleService = roleService;
        this.validationComponent = validationComponent;
    }


    //user registration
    @Transactional(rollbackOn = {NoContentException.class, DuplicateException.class, EmailFailureException.class})
    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody RegistrationDTO registrationDTO, BindingResult result) throws DuplicateException, EmailFailureException, NoContentException, InvalidDataException {

        //validation
        ApiResponse response = validationComponent.handleValidationErrors(result);

        if (response.getFieldErrors() != null) {
            return ResponseEntity.badRequest().body(response);
        }

        //check if passwords match
        if (!Objects.equals(registrationDTO.getPassword(), registrationDTO.getConfirmPassword())) {
            throw new InvalidDataException("رمز عبور با تکرار آن برابر نیست.");
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

            //setting response
            response.setSuccess(true);
            response.setSuccessMessage("حساب کاربری با موفقیت ایجاد شد.");

            Map<String, Object> data = new HashMap<>();
            data.put("user", user);
            response.setData(data);

            return ResponseEntity.ok(response);

    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> loginUser(@Valid @RequestBody LoginDTO loginDTO, BindingResult result) throws UserAccessDeniedException {

        //validation
        ApiResponse response = validationComponent.handleValidationErrors(result);

        if (response.getFieldErrors() != null) {
            return ResponseEntity.badRequest().body(response);
        }

        String jwt = null;

        try {
            jwt = userService.loginUser(loginDTO);


        } catch (UserNotVerifiedException exception) {

            String message = "اکانت کاربر فعال نشده ";
            if (exception.isNewEmailSent()){
                message += "ایمیل فعال سازی مجددا ارسال شد";
            }

            ApiOverallError overallError = new ApiOverallError(
                    message,
                    HttpStatus.FORBIDDEN,
                    ZonedDateTime.now(ZoneId.of("Z"))
            );

            response.setOverallError(overallError);
            response.setSuccess(false);

            Map<String, Object> data = new HashMap<>();
            data.put("jwt", null);
            response.setData(data);

            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);

        }
        catch (EmailFailureException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }


        if (jwt == null){
            throw new UserAccessDeniedException("نام کاربری یا رمز عبور اشتباه است.");
        }else {
            response.setSuccess(true);
            response.setSuccessMessage("کاربر با موفقیت وارد شد.");
            Map<String, Object> data = new HashMap<>();
            data.put("jwt", jwt);
            response.setData(data);

            return ResponseEntity.ok(response);
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
    @JsonView(Views.UserInfo.class)
    public User getLoggedInUserProfile(@AuthenticationPrincipal User user) {
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
