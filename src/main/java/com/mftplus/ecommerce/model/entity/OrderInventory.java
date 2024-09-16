package com.mftplus.ecommerce.model.entity;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
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

    @JsonView(Views.OrderList.class)
    @Column(name = "o_quantity", nullable = false)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "inventory_id")
    @JsonView(Views.OrderList.class)
    private Inventory inventory;

    @Column(name = "single_product_price", nullable = false)
    @JsonView(Views.singleOrder.class)
    private Integer singleProductPrice;

    @Column(name = "single_product_off_percent", nullable = false)
    @JsonView(Views.singleOrder.class)
    private Integer singleProductOffPercent;

//    @JsonView(Views.Order.class)
//    @Transient
//    public Inventory getInventory() {
//        return this.getInventory();
//    }


    @Transient
//    @JsonView(Views.OrderList.class)
    public Integer getTotalPrice(){
        Integer price = getSingleProductPrice();
        Integer offPercent = getSingleProductOffPercent();
        Integer finalPrice = (price - (price*offPercent)/100);

        return finalPrice * getQuantity();
    }

    @Transient
    public Integer getTotalOffPrice(){
        Integer price = getSingleProductPrice();
        Integer offPercent = getSingleProductOffPercent();
        Integer finalOffPrice = (price*offPercent)/100;

        return finalOffPrice * getQuantity();
    }


}