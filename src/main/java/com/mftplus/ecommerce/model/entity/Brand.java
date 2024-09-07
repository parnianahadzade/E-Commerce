package com.mftplus.ecommerce.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "brandEntity")
@Table(name = "brand_tbl")
public class Brand extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @JsonView({Views.singleProduct.class,Views.BrandName.class})
    private Long id;

    @Column(name = "b_name", nullable = false, unique = true, length = 20)
    @JsonView({Views.singleProduct.class,Views.BrandName.class})
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "brand")
    private List<Product> products = new ArrayList<>();

    @Column(name = "b_explanation", nullable = false, length = 100)
    @JsonView(Views.singleProduct.class)
    private String explanation;

}