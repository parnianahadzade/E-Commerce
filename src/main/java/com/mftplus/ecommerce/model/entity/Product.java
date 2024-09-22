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
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "productEntity")
@Table(name = "product_tbl", uniqueConstraints = {@UniqueConstraint(columnNames = {"p_name", "color_id"})})
public class Product extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @JsonView({Views.ProductList.class,Views.OrderList.class})
    private Long id;

    @Column(name = "p_code", length = 25, nullable = false)
    @JsonView(Views.singleProduct.class)
    private String code;

    @Column(name = "p_name", nullable = false, unique = true)
    @JsonView({Views.ProductList.class, Views.singleOrder.class})
    private String name;

    @Column(name = "p_description", nullable = false)
    @JsonView(Views.singleProduct.class)
    private String description;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    @JsonView(Views.singleProduct.class)
    private Brand brand;

    @OneToMany(mappedBy = "product")
    @JsonView(Views.ProductList.class)
    private List<Inventory> inventories = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "category_products_tbl",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "categories_id"))
    @JsonIgnore
    private List<Category> categories = new ArrayList<>();

    @OneToMany
    @JoinTable(name = "product_images_tbl")
    @JsonView(Views.singleProduct.class)
    private List<Image> images;

    @OneToOne
    @JoinColumn(name = "p_main_image_id")
    @JsonView({Views.ProductList.class, Views.OrderList.class})
    private Image mainImage;

    @ManyToOne
    @JoinColumn(name = "color_id")
    @JsonView({Views.singleProduct.class, Views.singleOrder.class})
    private Color color;

    @Column(name = "p_price",nullable = false)
    @JsonView(Views.ProductList.class)
    private Integer price;

    @Column(name = "p_off_percent",nullable = false)
    @JsonView(Views.ProductList.class)
    private Integer offPercent;

    @Column(name = "p_material", length = 10, nullable = false)
    @JsonView(Views.singleProduct.class)
    private String material;

    @Column(name = "p_pattern", length = 10, nullable = false)
    @JsonView(Views.singleProduct.class)
    private String pattern;

    @Column(name = "p_height", nullable = false)
    @JsonView(Views.singleProduct.class)
    private Integer height;

    @ManyToOne
    @JoinColumn(name = "main_category_id")
    @JsonView(Views.singleProduct.class)
    private Category mainCategory;

}