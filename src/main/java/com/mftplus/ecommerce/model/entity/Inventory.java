package com.mftplus.ecommerce.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.mftplus.ecommerce.model.entity.enums.Size;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "inventoryEntity")
@Table(name = "inventory_tbl")
@JsonView({Views.Product.class,Views.Category.class})
public class Inventory extends Base{
    //todo : is this a proper way for the relations? (color and product)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "i_quantity", nullable = false)
    @Min(value = 0, message = "Quantity must be equal or greater than 0.")
    private Integer quantity;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "product_size")
    private Size size;

}