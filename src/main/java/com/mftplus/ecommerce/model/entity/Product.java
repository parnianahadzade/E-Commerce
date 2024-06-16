package com.mftplus.ecommerce.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "productEntity")
@Table(name = "product_tbl")
public class Product extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "p_name", nullable = false, unique = true, length = 20)
    @Pattern(regexp = "^[A-Za-z]{3,20}$",message = "incorrect name!")
    private String name;

    @Column(name = "short_description", nullable = false)
    @Pattern(regexp = "^[A-Za-z]{5,}$",message = "incorrect short description!")
    private String shortDescription;

    @Column(name = "long_description", nullable = false)
    @Pattern(regexp = "^[A-Za-z]{10,}$",message = "incorrect long description!")
    private String longDescription;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @OneToMany(mappedBy = "product", orphanRemoval = true, cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private List<Inventory> inventories = new ArrayList<>();

    @ToString.Exclude
    @ManyToMany
    @JoinTable(name = "category_products_tbl",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "categories_id"))
    private List<Category> categories = new ArrayList<>();

}