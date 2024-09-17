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
@Entity(name = "imageEntity")
@Table(name = "image_tbl")
public class Image extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @JsonView({Views.ProductList.class, Views.OrderList.class, Views.singleProduct.class})
    private Long id;

    @Lob
    @Column(name = "i_file_path",nullable = false)
    @JsonView({Views.ProductList.class, Views.OrderList.class, Views.singleProduct.class})
    private String filePath;

}