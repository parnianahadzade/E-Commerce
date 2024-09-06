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
public class Color extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @JsonView({Views.ColorName.class,Views.singleProduct.class})
    private Long id;

    @Column(name = "c_name", nullable = false, unique = true, length = 20)
    @JsonView({Views.ColorName.class,Views.singleProduct.class})
    private String name;

    @Column(name = "hex_code", nullable = false, length = 20)
    @JsonView({Views.ColorName.class,Views.singleProduct.class})
    private String hexCode;

}