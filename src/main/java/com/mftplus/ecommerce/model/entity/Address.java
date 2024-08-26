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
@JsonView(Views.PersonInfo.class)
public class Address extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JsonView(Views.PersonInfo.class)
    @Column(name = "a_address_line", nullable = false, length = 50)
    private String addressLine;

    @Column(name = "a_postal_code", nullable = false, length = 10)
    private String postalCode;

    @JsonIgnore
    @OneToOne(mappedBy = "address")
    private Person person;

}