package com.mftplus.ecommerce.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "personEntity")
@Table(name = "person_tbl")
public class Person extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "p_first_name", nullable = false, length = 20)
    @Pattern(regexp = "^[A-Za-z]{3,20}$",message = "incorrect first name!")
    private String firstName;

    @Column(name = "p_last_name", nullable = false, length = 20)
    @Pattern(regexp = "^[A-Za-z]{3,20}$",message = "incorrect last name!")
    private String lastName;

    @Column(name = "p_phone_number", nullable = false, length = 11)
    @Pattern(regexp = "^[0-9]{11}$",message = "incorrect phone number!")
    private String phoneNumber;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private User user;

}