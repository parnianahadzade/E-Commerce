package com.mftplus.ecommerce.model.entity;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
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
    @JsonView(Views.PersonInfo.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JsonView(Views.PersonInfo.class)
    @Column(name = "p_first_name", nullable = false, length = 20)
    private String firstName;

    @JsonView(Views.PersonInfo.class)
    @Column(name = "p_last_name", nullable = false, length = 20)
    private String lastName;

    @JsonView(Views.PersonInfo.class)
    @Column(name = "p_phone_number", nullable = false, length = 11)
    private String phoneNumber;

    @JsonView(Views.PersonInfo.class)
    @OneToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}