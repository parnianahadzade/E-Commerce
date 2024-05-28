package com.mftplus.ecommerce.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "userEntity")
@Table(name = "user_tbl")
public class User extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq",sequenceName = "user_seq",allocationSize = 1)
    @Column(name = "u_id", nullable = false)
    private Long id;

    @Column(name = "u_username", nullable = false, unique = true, length = 15)
    @Pattern(regexp = "^[a-z]{2,15}$",message = "incorrect username !")
    private String username;

    @Column(name = "u_password", nullable = false, length = 15)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{5,}$",message = "Minimum five characters, at least one letter and one number!")
    private String password;

    @Email(message = "Incorrect Email Format!")
    @Column(name = "u_email", unique = true, length = 320)
    private String email;

    @Column(name = "u_first_name", nullable = false, length = 10)
    @Pattern(regexp = "^[a-z]{2,10}$",message = "incorrect first name!")
    private String firstName;

    @Column(name = "u_last_name", nullable = false, length = 10)
    @Pattern(regexp = "^[a-z]{2,10}$",message = "incorrect last name!")
    private String lastName;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();

}