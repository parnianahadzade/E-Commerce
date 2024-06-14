package com.mftplus.ecommerce.model.entity;

import jakarta.persistence.*;
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
    private String name;

    @Column(name = "short_description", nullable = false)
    private String shortDescription;

    @Column(name = "long_description", nullable = false)
    private String longDescription;

    @Column(name = "p_price", nullable = false)
    private Double price;

    @Column(name = "p_off_percent")
    private String offPercent;

    @ManyToMany(mappedBy = "products")
    private List<Category> categories = new ArrayList<>();

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;


    @OneToMany(mappedBy = "product", orphanRemoval = true)
    private List<Inventory> inventories = new ArrayList<>();

}