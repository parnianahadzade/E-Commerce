package com.mftplus.ecommerce.model.entity;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "colorEntity")
@Table(name = "color_tbl")
@JsonView({Views.singleProduct.class,Views.Category.class})
public class Color extends Base{
    @JsonView(Views.ColorName.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JsonView(Views.ColorName.class)
    @Column(name = "c_name", nullable = false, unique = true, length = 20)
    private String name;

    @Column(name = "hex_code", nullable = false, length = 20)
    private String hexCode;

}