package com.mftplus.ecommerce.model.entity;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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

    @JsonView(Views.PersonInfo.class)
    @Column(name = "p_first_name", nullable = false, length = 20)
    @NotBlank(message = "لطفا این قسمت را خالی نگذازید.")
    @Pattern(regexp = "^[A-Za-zآ-ی]{3,20}$",message = "نام نادرست است.")
    private String firstName;

    @JsonView(Views.PersonInfo.class)
    @Column(name = "p_last_name", nullable = false, length = 20)
    @NotBlank(message = "لطفا این قسمت را خالی نگذازید.")
    @Pattern(regexp = "^[A-Za-zآ-ی]{3,20}$",message = "نام خانوادگی نادرست است.")
    private String lastName;

    @JsonView(Views.PersonInfo.class)
    @Column(name = "p_phone_number", nullable = false, length = 11)
    @NotBlank(message = "لطفا این قسمت را خالی نگذازید.")
    @Pattern(regexp = "^[0-9]{11}$",message = "تلفن همراه نادرست است.")
    private String phoneNumber;

    @JsonView(Views.PersonInfo.class)
    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private User user;

}