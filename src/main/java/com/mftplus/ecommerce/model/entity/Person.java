package com.mftplus.ecommerce.model.entity;

import com.mftplus.ecommerce.model.entity.entityListener.PersonListener;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDate;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "personEntity")
@Table(name = "person_tbl")
@EntityListeners(PersonListener.class)
public class Person extends Base implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_seq")
    @SequenceGenerator(name = "person_seq",sequenceName = "person_seq",allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Email(message = "Incorrect Email Format!")
    @Column(name = "p_email", nullable = false, unique = true, length = 320)
    private String email;

    @Column(name = "p_first_name", nullable = false, length = 10)
    @Pattern(regexp = "^[a-z]{2,10}$",message = "incorrect first name!")
    private String firstName;

    @Column(name = "p_last_name", nullable = false, length = 10)
    @Pattern(regexp = "^[a-z]{2,10}$",message = "incorrect last name!")
    private String lastName;

    @OneToOne(mappedBy = "person", orphanRemoval = true)
    private User user;

    @Column(name = "birth_date")
    @Past
    private LocalDate birthDate;

}