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
@Entity(name = "categoryEntity")
@Table(name = "category_tbl")
public class Category extends Base{
    @JsonView({Views.singleProduct.class, Views.Category.class})
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JsonView({Views.singleProduct.class, Views.Category.class})
    @Column(name = "c_name", nullable = false, unique = true, length = 20)
    private String name;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category parentCategory;

    @JsonView(Views.Category.class)
    @OneToMany(mappedBy = "parentCategory")
    private List<Category> childCategories = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "categories")
    private List<Product> products = new ArrayList<>();

    @JsonView({Views.singleProduct.class, Views.Category.class})
    public List<String> getCategoryPath() {
        List<String> categoryPath = new ArrayList<>();
        categoryPath.add(this.getName());
        Category currentCategory = this;

        while (currentCategory.getParentCategory() != null) {
            categoryPath.add(currentCategory.getParentCategory().getName());
//            categoryPath = currentCategory.getParentCategory().getName() + " > " + categoryPath;
            currentCategory = currentCategory.getParentCategory();
        }

        return categoryPath;
    }

}