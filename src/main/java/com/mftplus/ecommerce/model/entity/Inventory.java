package com.mftplus.ecommerce.model.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.mftplus.ecommerce.model.entity.enums.Size;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "inventoryEntity")
@Table(name = "inventory_tbl")
@JsonView({Views.ProductList.class,Views.Category.class,Views.Order.class})
public class Inventory extends Base{
    //todo : is this a proper way for the relations? (color and product)
    @JsonView({Views.ProductList.class,Views.Category.class,Views.Order.class})
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JsonView({Views.ProductList.class,Views.Category.class,Views.Order.class})
    @Column(name = "i_quantity", nullable = false)
    @Min(value = 0, message = "Quantity must be equal or greater than 0.")
    private Integer quantity;

    @JsonView(Views.Order.class)
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @JsonView({Views.ProductList.class,Views.Category.class,Views.Order.class})
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "product_size")
    private Size size;

}