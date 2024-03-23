package com.mftplus.ecommerce.model.entity;

import com.mftplus.ecommerce.model.entity.entityListener.UserListener;
import jakarta.persistence.*;
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
@Table(name = "user_tbl")
@EntityListeners(UserListener.class)
public class User {
    @Id
    @Column(name = "u_username", nullable = false, length = 15)
    @Size(min = 5,max = 15, message = "incorrect size")
    private String username;

    @Column(name = "u_password", nullable = false, length = 15)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{5,}$",message = "Minimum five characters, at least one letter and one number!")
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();

    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name = "person_id")
    private Person person;

}