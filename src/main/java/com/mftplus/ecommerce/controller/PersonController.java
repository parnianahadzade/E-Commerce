package com.mftplus.ecommerce.controller;

import com.mftplus.ecommerce.model.entity.Person;
import com.mftplus.ecommerce.service.PersonServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Slf4j
@Controller
public class PersonController {
    private final PersonServiceImpl personService;

    public PersonController(PersonServiceImpl personService) {
        this.personService = personService;
    }

    //person table
    @GetMapping("/personList")
    public String showPersonList(Model model){
        log.info("Person List - Get");
        try {
            //for th:object="${userObj}"
            model.addAttribute("person", new Person());
            model.addAttribute("personList", personService.findAllByDeletedFalse());
            return "/tables/personTable";
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    //personal info page
    @GetMapping ("/personalInfo")
    public String showPersonalInfo(Model model){
        log.info("Personal Info - Get");
        try {
            //for th:object="${userObj}"
            model.addAttribute("person", new Person());
//            model.addAttribute("personList", personService.findAll());
            return "personalInfo";
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    //person form
    @GetMapping("/person")
    public String showForm(Model model) {
        log.info("Person Form - Get");
        try {
            //for th:object="${userObj}"
            model.addAttribute("person", new Person());
//            model.addAttribute("personList", personService.findAll());
            return "/forms/personForm";
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    //person save
    @PostMapping(value = "/save",params = "action=save")
    public String save(@Valid Person person, BindingResult result,Model model){
        log.info("Person - Post");
        if (result.hasErrors()){
            log.error(result.getAllErrors().toString());
            return "/forms/personForm";
        }
        try {
            personService.save(person);
            log.info("Person Saved");
            log.info(person.toString());
            model.addAttribute("msg", "Person Saved");
            return "/forms/personForm";
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    //person edit page
    @GetMapping(value = "/person/edit")
    public String edit(@RequestParam Long id, Model model) {
        log.info("Person - edit page");
        try {
            Optional<Person> person = personService.findById(id);
            model.addAttribute("person",person);
            return "/forms/personEdit";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    //person edit
    @PostMapping(value = "/save",params = "action=edit")
    public String editForm(@Valid Person person, Model model){
        log.info("Person - Edit");
        try {
            Long id = person.getId();
            Optional<Person> person1 = personService.findById(id);
            if (person1.isPresent()){
                personService.edit(person);
                log.info("Person Edited");
                model.addAttribute("person", person);
                model.addAttribute("msg", "Person Edited");
                return "/forms/personEdit";
            }
            return "/forms/personForm";
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    //person logical remove
    @PostMapping("/delete")
    public String editForm(@ModelAttribute("id") Long id,Model model){
        log.info("Person - Delete");
        try {
            Optional<Person> person = personService.findById(id);
            if (person.isPresent()){
                personService.logicalRemove(id);
                log.info("Person Removed");
                model.addAttribute("msg", "Person Removed");
                return "/tables/personTable";
            }
            return "/forms/personForm";
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
