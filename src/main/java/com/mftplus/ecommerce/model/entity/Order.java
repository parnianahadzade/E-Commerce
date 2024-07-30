package com.mftplus.ecommerce.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.mftplus.ecommerce.model.entity.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "orderEntity")
@Table(name = "order_tbl")
public class Order extends Base{
    //invoice

    @JsonView(Views.Order.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JsonView(Views.Order.class)
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @JsonView(Views.Order.class)
    @ManyToOne(optional = false)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @JsonView(Views.Order.class)
    @Column(name = "date_created", nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateCreated;

    @JsonView(Views.Order.class)
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "order_status")
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order", orphanRemoval = true)
    private List<OrderInventory> orderInventories = new ArrayList<>();

    @JsonView(Views.Order.class)
    @Transient
    public Integer getTotalOrderPrice(){
        Integer sum = 0;
        orderInventories = getOrderInventories();
        for (OrderInventory orderInventory : orderInventories) {
            sum += orderInventory.getTotalPrice();
        }
        return sum;
    }

    @JsonView(Views.Order.class)
    @Transient
    public int getNumberOfProducts(){
        return this.orderInventories.size();
    }

}