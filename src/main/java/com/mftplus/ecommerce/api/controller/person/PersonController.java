package com.mftplus.ecommerce.api.controller.person;

import com.fasterxml.jackson.annotation.JsonView;
import com.mftplus.ecommerce.api.dto.UserIdentificationDTO;
import com.mftplus.ecommerce.exception.NoContentException;
import com.mftplus.ecommerce.exception.UserAccessDeniedException;
import com.mftplus.ecommerce.exception.UserIdentification;
import com.mftplus.ecommerce.exception.component.ApiExceptionComponent;
import com.mftplus.ecommerce.exception.dto.ApiExceptionResponse;
import com.mftplus.ecommerce.model.entity.Address;
import com.mftplus.ecommerce.model.entity.Person;
import com.mftplus.ecommerce.model.entity.User;
import com.mftplus.ecommerce.model.entity.Views;
import com.mftplus.ecommerce.service.impl.AddressServiceImpl;
import com.mftplus.ecommerce.service.impl.PersonServiceImpl;
import com.mftplus.ecommerce.service.impl.UserServiceImpl;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${apiPrefix}/person")
@CrossOrigin
public class PersonController {

    private final PersonServiceImpl personService;

    private final AddressServiceImpl addressService;

    private final UserServiceImpl userService;


    public PersonController(PersonServiceImpl personService, AddressServiceImpl addressService, UserServiceImpl userService) {
        this.personService = personService;
        this.addressService = addressService;
        this.userService = userService;
    }

    @JsonView(Views.PersonInfo.class)
    @GetMapping
    public Person findByUser(@AuthenticationPrincipal User user) throws NoContentException {
        return personService.findByUserUsernameAndDeletedFalse(user.getUsername());
    }

    //user profile creation
    @Transactional(rollbackOn = {NoContentException.class,UserIdentification.class})
    @PostMapping("/save")
    public ResponseEntity savePerson(@Valid @RequestBody UserIdentificationDTO userIdentificationDTO,
                                     BindingResult result, @AuthenticationPrincipal User user) throws UserIdentification, NoContentException {

        if (user.isIdentified()) {
            throw new UserIdentification("User is already identified.");
        }

        //validating inputs
        ResponseEntity<ApiExceptionResponse> responseEntity = ApiExceptionComponent.handleValidationErrors(result);
        if (responseEntity != null) {
            return responseEntity;
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

        return ResponseEntity.ok().build();
    }


    //user profile update
    @Transactional(rollbackOn = {UserAccessDeniedException.class, NoContentException.class})
    @PutMapping("/update/{personId}")
    public ResponseEntity updatePerson(@Valid @RequestBody UserIdentificationDTO userIdentificationDTO,
                                     BindingResult result, @AuthenticationPrincipal User user,
                                     @PathVariable Long personId) throws NoContentException, UserAccessDeniedException {

        if (!personService.userHasPermissionToPerson(user, personId)){
            throw new UserAccessDeniedException("عدم دسترسی به اطلاعات کاربر");
        }

        //validating inputs
        ResponseEntity<ApiExceptionResponse> responseEntity = ApiExceptionComponent.handleValidationErrors(result);
        if (responseEntity != null) {
            return responseEntity;
        }

        Address existingAddress = addressService.findByPersonIdAndDeletedFalse(personId);
        Address newAddress = new Address();
        newAddress.setId(existingAddress.getId());
        newAddress.setAddressLine(userIdentificationDTO.getAddressLine());
        newAddress.setPostalCode(userIdentificationDTO.getPostalCode());
        newAddress.setVersionId(existingAddress.getVersionId());
//        addressService.update(newAddress);


        Person newPerson = new Person();
        newPerson.setId(personId);
        newPerson.setFirstName(userIdentificationDTO.getFirstName());
        newPerson.setLastName(userIdentificationDTO.getLastName());
        newPerson.setPhoneNumber(userIdentificationDTO.getPhoneNumber());
        newPerson.setAddress(newAddress);

        personService.update(newPerson);

        return ResponseEntity.ok().build();

    }

}
