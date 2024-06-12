package com.mftplus.ecommerce.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "b_name", nullable = false, unique = true, length = 20)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "brand", orphanRemoval = true)
    private List<Product> products = new ArrayList<>();

}