package com.mftplus.ecommerce.model.entity;

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
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "productEntity")
@Table(name = "product_tbl", uniqueConstraints = {@UniqueConstraint(columnNames = {"p_name", "color_id"})})
public class Product extends Base{
    @JsonView({Views.ProductList.class,Views.Category.class})
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JsonView({Views.singleProduct.class,Views.Category.class})
    @Column(name = "p_code", length = 25, nullable = false)
    private String code;

    @JsonView({Views.ProductList.class,Views.Category.class})
    @Column(name = "p_name", nullable = false, unique = true, length = 20)
    private String name;

    @JsonView({Views.singleProduct.class,Views.Category.class})
    @Column(name = "p_description", nullable = false, length = 100)
    private String description;

    @JsonView({Views.singleProduct.class,Views.Category.class})
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @JsonView({Views.ProductList.class,Views.Category.class})
    @OneToMany(mappedBy = "product", cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private List<Inventory> inventories = new ArrayList<>();

    @JsonView({Views.singleProduct.class})
    @ManyToMany
    @JoinTable(name = "category_products_tbl",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "categories_id"))
    private List<Category> categories = new ArrayList<>();

    @JsonView({Views.singleProduct.class,Views.Category.class})
    @OneToMany
    @JoinTable(name = "product_images_tbl")
    private List<Image> images;

    @JsonView({Views.ProductList.class,Views.Category.class})
    @OneToOne
    @JoinColumn(name = "p_main_image_id")
    private Image mainImage;

    @JsonView({Views.singleProduct.class,Views.Category.class})
    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;

    @JsonView({Views.ProductList.class,Views.Category.class})
    @Column(name = "p_price",nullable = false)
    private Integer price;

    @JsonView({Views.ProductList.class,Views.Category.class})
    @Column(name = "p_off_percent",nullable = false)
    private Integer offPercent;

    @Column(name = "p_material", length = 10, nullable = false)
    private String material;

    @Column(name = "p_pattern", length = 10, nullable = false)
    private String pattern;

    @Column(name = "p_height", length = 10, nullable = false)
    private String height;

}