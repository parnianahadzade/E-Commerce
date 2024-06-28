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
@JsonView({Views.Product.class,Views.Category.class})
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "i_name", length = 50)
    private String name;

    @Column(name = "i_type", length = 50)
    private String type;

    @Lob
    @Column(name = "i_file_path")
    private String filePath;

}