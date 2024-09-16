package com.mftplus.ecommerce.api.controller.person;

import com.fasterxml.jackson.annotation.JsonView;
import com.mftplus.ecommerce.api.dto.UserIdentificationDTO;
import com.mftplus.ecommerce.exception.NoContentException;
import com.mftplus.ecommerce.exception.UserAccessDeniedException;
import com.mftplus.ecommerce.exception.UserIdentificationException;
import com.mftplus.ecommerce.exception.component.ApiValidationComponent;
import com.mftplus.ecommerce.exception.dto.ApiResponse;
import com.mftplus.ecommerce.model.entity.*;
import com.mftplus.ecommerce.service.impl.AddressServiceImpl;
import com.mftplus.ecommerce.service.impl.PersonServiceImpl;
import com.mftplus.ecommerce.service.impl.UserServiceImpl;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("${apiPrefix}/person")
@Slf4j
@CrossOrigin
public class PersonController {

    private final PersonServiceImpl personService;

    private final AddressServiceImpl addressService;

    private final UserServiceImpl userService;

    private final ApiValidationComponent validationComponent;


    public PersonController(PersonServiceImpl personService, AddressServiceImpl addressService, UserServiceImpl userService, ApiValidationComponent validationComponent) {
        this.personService = personService;
        this.addressService = addressService;
        this.userService = userService;
        this.validationComponent = validationComponent;
    }

    //person find by user
    @GetMapping
    @JsonView(Views.PersonAndUserInfo.class)
    public Person findPersonByUser(@AuthenticationPrincipal User user) throws NoContentException {
        return personService.findByUserUsernameAndDeletedFalse(user.getUsername());
    }


    //todo : findAll (criteria)


    //person save or user profile creation, admin has no access
    @PostMapping("/save")
    @Transactional(rollbackOn = {NoContentException.class, UserIdentificationException.class})
    public ResponseEntity<ApiResponse> savePerson(@Valid @RequestBody UserIdentificationDTO userIdentificationDTO,
                                     BindingResult result, @AuthenticationPrincipal User user) throws UserIdentificationException, NoContentException {

        if (user.isIdentified()) {
            throw new UserIdentificationException("پروفایل کاربر قبلا ساخته شده.");
        }

        //validating inputs
        ApiResponse response = validationComponent.handleValidationErrors(result);

        if (response.getFieldErrors() != null) {
            return ResponseEntity.badRequest().body(response);
        }

        Address address = new Address();
        address.setAddressLine(userIdentificationDTO.getAddressLine());
        address.setPostalCode(userIdentificationDTO.getPostalCode());
        addressService.save(address);

        user.setIdentified(true);
        userService.update(user);

        Person person = new Person();
        person.setUser(user);
        person.setFirstName(userIdentificationDTO.getFirstName());
        person.setLastName(userIdentificationDTO.getLastName());
        person.setPhoneNumber(userIdentificationDTO.getPhoneNumber());
        person.setAddress(address);

        personService.save(person);

        response.setSuccess(true);
        response.setSuccessMessage("پروفایل با موفقیت ایجاد شد.");

        Map<String, Object> data = new HashMap<>();
        data.put("person", person);
        response.setData(data);

        return ResponseEntity.ok(response);
    }


    //person update or user profile update , admin also has access
    @PostMapping("/update/{personId}")
    @Transactional(rollbackOn = {UserAccessDeniedException.class, NoContentException.class})
    public ResponseEntity<ApiResponse> updatePerson(@Valid @RequestBody UserIdentificationDTO userIdentificationDTO,
                                     BindingResult result, @AuthenticationPrincipal User user,
                                     @PathVariable Long personId) throws NoContentException, UserAccessDeniedException {

        if (!personService.userHasPermissionToPerson(user, personId)){
            throw new UserAccessDeniedException("عدم دسترسی به اطلاعات کاربر");
        }

        //validating inputs
        ApiResponse response = validationComponent.handleValidationErrors(result);

        if (response.getFieldErrors() != null) {
            return ResponseEntity.badRequest().body(response);
        }

        Address existingAddress = addressService.findByPersonIdAndDeletedFalse(personId);
        Address newAddress = new Address();
        newAddress.setId(existingAddress.getId());
        newAddress.setAddressLine(userIdentificationDTO.getAddressLine());
        newAddress.setPostalCode(userIdentificationDTO.getPostalCode());
        newAddress.setVersionId(existingAddress.getVersionId());

        Person newPerson = new Person();
        newPerson.setId(personId);
        newPerson.setFirstName(userIdentificationDTO.getFirstName());
        newPerson.setLastName(userIdentificationDTO.getLastName());
        newPerson.setPhoneNumber(userIdentificationDTO.getPhoneNumber());
        newPerson.setAddress(newAddress);

        personService.update(newPerson);

        response.setSuccess(true);
        response.setSuccessMessage("پروفایل با موفقیت بروزرسانی شد.");

        Map<String, Object> data = new HashMap<>();
        data.put("person", newPerson);
        response.setData(data);

        return ResponseEntity.ok(response);

    }

}
