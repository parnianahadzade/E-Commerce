package com.mftplus.ecommerce.component;

import com.mftplus.ecommerce.model.entity.Person;
import com.mftplus.ecommerce.model.entity.Role;
import com.mftplus.ecommerce.model.entity.User;
import com.mftplus.ecommerce.repository.PersonRepository;
import com.mftplus.ecommerce.repository.RoleRepository;
import com.mftplus.ecommerce.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AdminInitializer implements CommandLineRunner {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PersonRepository personRepository;

    public AdminInitializer(UserRepository userRepository, RoleRepository roleRepository, PersonRepository personRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.personRepository = personRepository;
    }

    @Override
    public void run(String... args) {
        if (roleRepository.findByNameAndDeletedFalse("admin").isEmpty() &&
                roleRepository.findByNameAndDeletedFalse("user").isEmpty() &&
                userRepository.findByUsernameIgnoreCaseAndDeletedFalse("admin").isEmpty()) {

            //person
            Person adminPerson = new Person();
            adminPerson.setFirstName("ادمین");
            adminPerson.setLastName("ادمین");
            adminPerson.setPostalCode("1489633937");
            adminPerson.setPhoneNumber("09123896543");
            adminPerson.setAddressLine("دهکده المپیک، زیبا دششت، خ لاله");
            personRepository.save(adminPerson);

            //role
            Role admin = new Role();
            admin.setName("admin");
            roleRepository.save(admin);

            Role user = new Role();
            user.setName("user");
            roleRepository.save(user);

            List<Role> roles = new ArrayList<>();
            roles.add(admin);
            roles.add(user);

            //user
            User adminUser = new User();
            adminUser.setUsername("Admin");
            adminUser.setPassword("$2a$10$hBn5gu6cGelJNiE6DDsaBOmZgyumCSzVwrOK/37FWgJ6aLIdZSSI2");
            adminUser.setEmail("admin@email.com");
            adminUser.setEmailVerified(true);
            adminUser.setRoles(roles);
            adminUser.setVersionId(0L);
            adminUser.setPerson(adminPerson);
            adminUser.setIdentified(true);
            userRepository.save(adminUser);

        }
    }
}
