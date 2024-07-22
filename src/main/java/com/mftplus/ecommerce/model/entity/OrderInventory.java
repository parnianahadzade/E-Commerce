package com.mftplus.ecommerce.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "orderInventoryEntity")
@Table(name = "order_inventory_tbl")
public class OrderInventory extends Base{
    //invoice item

    // TODO: 7/23/2024  matching inventory quantity with the quantity here
    @EmbeddedId
    @JsonIgnore
    private OrderInventoryPK pk;

    @JsonView(Views.Order.class)
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @JsonView(Views.Order.class)
    @Transient
    public Inventory getInventory() {
        return this.pk.getInventory();
    }

    @JsonView(Views.Order.class)
    @Transient
    public Integer getTotalPrice(){
        Integer price = getInventory().getProduct().getPrice();
        Integer offPercent = getInventory().getProduct().getOffPercent();
        Integer finalPrice = (price - (price*offPercent)/100);

        return finalPrice * getQuantity();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public OrderInventory(Order order, Inventory inventory, Integer quantity) {
        pk = new OrderInventoryPK();
        pk.setOrder(order);
        pk.setInventory(inventory);
        this.quantity = quantity;
    }

}