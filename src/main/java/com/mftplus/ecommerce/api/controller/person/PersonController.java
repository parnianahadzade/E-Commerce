package com.mftplus.ecommerce.api.controller.person;

import com.fasterxml.jackson.annotation.JsonView;
import com.mftplus.ecommerce.exception.NoContentException;
import com.mftplus.ecommerce.model.entity.Person;
import com.mftplus.ecommerce.model.entity.User;
import com.mftplus.ecommerce.model.entity.Views;
import com.mftplus.ecommerce.service.impl.PersonServiceImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${apiPrefix}/person")
@CrossOrigin
public class PersonController {

    private final PersonServiceImpl personService;

    public PersonController(PersonServiceImpl personService) {
        this.personService = personService;
    }

    @JsonView(Views.PersonInfo.class)
    @GetMapping
    public Person findByUser(@AuthenticationPrincipal User user) throws NoContentException {
        return personService.findByUserUsernameAndDeletedFalse(user.getUsername());
    }


}
