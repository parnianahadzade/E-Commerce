package com.mftplus.ecommerce.model.entity;

import com.mftplus.ecommerce.model.entity.entityListener.UserListener;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
@EntityListeners(UserListener.class)
@Table(name = "user_tbl")
public class LocalUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq",sequenceName = "user_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(min = 5,max = 15, message = "incorrect size")
    @Column(name = "u_username", nullable = false, unique = true, length = 15)
    private String username;

    @Column(name = "u_password", nullable = false, length = 1000)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{5,}$",message = "Minimum five characters, at least one letter and one number!")
    private String password;

    @Email
    @Column(name = "u_email", nullable = false, unique = true, length = 320)
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