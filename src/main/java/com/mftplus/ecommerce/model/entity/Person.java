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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @JsonView({Views.PersonAndUserInfo.class, Views.UserInfo.class})
    private Long id;

    @Column(name = "p_first_name", nullable = false, length = 20)
    @JsonView({Views.PersonAndUserInfo.class, Views.UserInfo.class, Views.OrderList.class})
    private String firstName;

    @Column(name = "p_last_name", nullable = false, length = 20)
    @JsonView({Views.PersonAndUserInfo.class, Views.UserInfo.class, Views.OrderList.class})
    private String lastName;

    @Column(name = "p_phone_number", nullable = false, length = 11)
    @JsonView({Views.PersonAndUserInfo.class, Views.UserInfo.class})
    private String phoneNumber;

    @OneToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "address_id")
    @JsonView({Views.PersonAndUserInfo.class, Views.UserInfo.class})
    private Address address;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonView(Views.PersonAndUserInfo.class)
    private User user;

}