package com.mftplus.ecommerce.model.entity;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "productEntity")
@Table(name = "product_tbl")
public class Product extends Base{
    @JsonView({Views.Product.class,Views.Category.class})
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JsonView({Views.Product.class,Views.Category.class})
    @Column(name = "p_name", nullable = false, unique = true, length = 20)
    @Pattern(regexp = "^[A-Za-z]{3,20}$",message = "incorrect name!")
    private String name;

    @JsonView({Views.Product.class,Views.Category.class})
    @Column(name = "short_description", nullable = false)
    @Pattern(regexp = "^[A-Za-z]{5,}$",message = "incorrect short description!")
    private String shortDescription;

    @JsonView({Views.Product.class,Views.Category.class})
    @Column(name = "long_description", nullable = false)
    @Pattern(regexp = "^[A-Za-z]{10,}$",message = "incorrect long description!")
    private String longDescription;

    @JsonView({Views.Product.class,Views.Category.class})
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @JsonView({Views.Product.class,Views.Category.class})
    @OneToMany(mappedBy = "product", orphanRemoval = true, cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private List<Inventory> inventories = new ArrayList<>();

    @JsonView({Views.Product.class})
    @ManyToMany
    @JoinTable(name = "category_products_tbl",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "categories_id"))
    private List<Category> categories = new ArrayList<>();

    @JsonView({Views.Product.class,Views.Category.class})
    @OneToMany
    @JoinTable(name = "product_images_tbl")
    private List<Image> images;

    @JsonView({Views.Product.class,Views.Category.class})
    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "p_main_image_id")
    private Image mainImage;

}