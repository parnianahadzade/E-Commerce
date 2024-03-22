package com.mftplus.ecommerce.controller;

import com.mftplus.ecommerce.model.entity.LocalUser;
import com.mftplus.ecommerce.service.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class UserController {
    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }


    @GetMapping("/user")
    public String showForm(Model model) {
        model.addAttribute("user", new LocalUser());
        model.addAttribute("userList", userService.findAll());
        return "userForm";
    }

    @PostMapping("/user/save")
    public String save(@Valid LocalUser user){
        try {
            userService.save(user);
            return "userForm";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
