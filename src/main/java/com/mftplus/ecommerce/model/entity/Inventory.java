package com.mftplus.ecommerce.model.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.mftplus.ecommerce.model.entity.enums.Size;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "inventoryEntity")
@Table(name = "inventory_tbl")
@JsonView(Views.Category.class)
public class Inventory extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @JsonView({Views.ProductList.class,Views.Category.class})
    private Long id;

    @Column(name = "i_quantity", nullable = false)
    @JsonView({Views.ProductList.class,Views.Category.class})
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "product_size")
    @JsonView({Views.ProductList.class,Views.Category.class})
    private Size size;

}