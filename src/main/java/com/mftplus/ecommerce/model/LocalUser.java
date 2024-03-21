package com.mftplus.ecommerce.model;

import jakarta.persistence.*;
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
@Entity
@Table(name = "local_user_tbl")
public class LocalUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "local_user_seq")
    @SequenceGenerator(name = "local_user_seq",sequenceName = "local_user_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "u_username", nullable = false, unique = true, length = 15)
    private String username;

    @Column(name = "u_password", nullable = false, length = 1000)
    private String password;

    @Column(name = "u_email", nullable = false, unique = true, length = 320)
    private String email;

    @Column(name = "u_first_name", nullable = false, length = 10)
    private String firstName;

    @Column(name = "u_last_name", nullable = false, length = 10)
    private String lastName;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();

}