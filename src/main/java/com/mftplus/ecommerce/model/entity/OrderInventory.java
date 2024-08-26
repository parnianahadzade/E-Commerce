package com.mftplus.ecommerce.model.entity;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity(name = "orderInventoryEntity")
@Table(name = "order_inventory_tbl", uniqueConstraints = {@UniqueConstraint(columnNames = {"order_id", "inventory_id"})})
public class OrderInventory extends Base{

    //invoice item

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JsonView(Views.Order.class)
    @Column(name = "o_quantity", nullable = false)
    private Integer quantity;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;

//    @JsonView(Views.Order.class)
//    @Transient
//    public Inventory getInventory() {
//        return this.getInventory();
//    }

    @JsonView(Views.Order.class)
    @Transient
    public Integer getTotalPrice(){
        Integer price = getInventory().getProduct().getPrice();
        Integer offPercent = getInventory().getProduct().getOffPercent();
        Integer finalPrice = (price - (price*offPercent)/100);

        return finalPrice * getQuantity();
    }


}