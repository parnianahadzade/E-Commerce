package com.mftplus.ecommerce.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "addressEntity")
@Table(name = "address_tbl")
public class Address extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JsonView(Views.Order.class)
    @Column(name = "address_line_1", nullable = false)
    private String addressLine1;

    @JsonView(Views.Order.class)
    @Column(name = "address_line_2")
    private String addressLine2;

    @Column(name = "city", nullable = false, length = 10)
    private String city;

    @Column(name = "country", nullable = false, length = 60)
    private String country;

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}