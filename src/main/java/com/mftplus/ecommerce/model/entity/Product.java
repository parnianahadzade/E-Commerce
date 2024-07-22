package com.mftplus.ecommerce.model.entity;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
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
@Table(name = "product_tbl", uniqueConstraints = {@UniqueConstraint(columnNames = {"p_name", "color_id"})})
public class Product extends Base{
    @JsonView({Views.ProductList.class,Views.Category.class,Views.Order.class})
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JsonView({Views.singleProduct.class,Views.Category.class})
    @Column(name = "p_code", length = 25)
    @Pattern(regexp = "^[0-9]{5,50}$",message = "incorrect code!")
    private String code;

    @JsonView({Views.ProductList.class,Views.Category.class,Views.Order.class})
    @Column(name = "p_name", nullable = false, unique = true, length = 20)
    @Pattern(regexp = "^[A-Za-z]{3,20}$",message = "incorrect name!")
    private String name;

    @JsonView({Views.singleProduct.class,Views.Category.class})
    @Column(name = "short_description", nullable = false)
    @Pattern(regexp = "^[A-Za-z]{5,}$",message = "incorrect short description!")
    private String shortDescription;

    @JsonView({Views.singleProduct.class,Views.Category.class})
    @Column(name = "long_description", nullable = false)
    @Pattern(regexp = "^[A-Za-z]{10,}$",message = "incorrect long description!")
    private String longDescription;

    @JsonView({Views.singleProduct.class,Views.Category.class})
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @JsonView({Views.ProductList.class,Views.Category.class})
    @OneToMany(mappedBy = "product", orphanRemoval = true, cascade = {CascadeType.PERSIST,CascadeType.MERGE})
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

    @JsonView({Views.ProductList.class,Views.Category.class,Views.Order.class})
    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "p_main_image_id")
    private Image mainImage;

    @JsonView({Views.singleProduct.class,Views.Category.class})
    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;

    @JsonView({Views.ProductList.class,Views.Category.class,Views.Order.class})
    @Column(name = "p_price")
    @Min(value = 1, message = "Price must be equal or greater than 1.")
    private Integer price;

    @JsonView({Views.ProductList.class,Views.Category.class,Views.Order.class})
    @Column(name = "p_off_percent")
    @Min(value = 0, message = "Off Percent must be equal or greater than 0.")
    private Integer offPercent;


}