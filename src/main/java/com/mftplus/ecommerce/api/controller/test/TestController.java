package com.mftplus.ecommerce.api.controller.test;

import com.mftplus.ecommerce.exception.DuplicateException;
import com.mftplus.ecommerce.exception.EmailFailureException;
import com.mftplus.ecommerce.model.entity.Role;
import com.mftplus.ecommerce.model.entity.User;
import com.mftplus.ecommerce.service.EncryptionService;
import com.mftplus.ecommerce.service.impl.RoleServiceImpl;
import com.mftplus.ecommerce.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("${apiPrefix}/test")
@Slf4j
@CrossOrigin
public class TestController {

    private final UserServiceImpl userService;

    private final RoleServiceImpl roleService;

    private final EncryptionService encryptionService;

    public TestController(UserServiceImpl userService, RoleServiceImpl roleService, EncryptionService encryptionService) {
        this.userService = userService;
        this.roleService = roleService;
        this.encryptionService = encryptionService;
    }

    @GetMapping
    public void test() throws DuplicateException, EmailFailureException {

        Role admin = new Role();
        admin.setName("admin");
        roleService.save(admin);

        Role user = new Role();
        user.setName("user");
        roleService.save(user);

        List<Role> roles = new ArrayList<>();
        roles.add(admin);
        roles.add(user);

        User adminUser = new User();
        adminUser.setUsername("Admin");
        adminUser.setPassword(encryptionService.encryptPassword("PasswordA123"));
        adminUser.setEmail("admin@email.com");
        adminUser.setEmailVerified(true);
        adminUser.setRoles(roles);
        adminUser.setVersionId(0L);
        userService.save(adminUser);


    }
}
