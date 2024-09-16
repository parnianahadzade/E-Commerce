package com.mftplus.ecommerce.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "addressEntity")
@Table(name = "address_tbl")
public class Address extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @JsonView({Views.PersonAndUserInfo.class, Views.UserInfo.class})
    private Long id;

    @Column(name = "a_address_line", nullable = false, length = 50)
    @JsonView({Views.PersonAndUserInfo.class, Views.UserInfo.class, Views.singleOrder.class})
    private String addressLine;

    @Column(name = "a_postal_code", nullable = false, length = 10)
    @JsonView({Views.PersonAndUserInfo.class, Views.UserInfo.class})
    private String postalCode;

    @JsonIgnore
    @OneToOne(mappedBy = "address")
    private Person person;

}