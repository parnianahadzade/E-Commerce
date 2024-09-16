package com.mftplus.ecommerce.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.mfathi91.time.PersianDate;
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
@Entity(name = "orderEntity")
@Table(name = "order_tbl")
public class Order extends Base{
    //invoice

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @JsonView(Views.OrderList.class)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonView({Views.OrderListAdminOnly.class, Views.singleOrder.class})
    private User user;

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    @JsonView(Views.singleOrder.class)
    private Address address;

    @Column(name = "o_date_created", nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateCreated;

    @Transient
    private String faDateCreated;

    @JsonView(Views.OrderList.class)
    public String getFaDateCreated() {
        return String.valueOf(PersianDate.fromGregorian(dateCreated));
    }

    public void setFaDateCreated(String faDateCreated) {
        this.dateCreated = PersianDate.parse(faDateCreated).toGregorian();
    }

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "order_status")
    @JsonView(Views.OrderList.class)
    @JsonSerialize(using = OrderStatusSerializer.class)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order")
    @JsonView(Views.OrderList.class)
    private List<OrderInventory> orderInventories = new ArrayList<>();

    @Column(name = "o_tracking_code", unique = true)
    @JsonView(Views.OrderList.class)
    private String trackingCode;

    @Transient
    @JsonView(Views.OrderList.class)
    public Integer getTotalOrderPrice(){
        Integer sum = 0;
        orderInventories = getOrderInventories();
        for (OrderInventory orderInventory : orderInventories) {
            sum += orderInventory.getTotalPrice();
        }
        return sum;
    }

    @Transient
    @JsonView(Views.OrderList.class)
    public Integer getTotalOrderOffPrice(){
        Integer sum = 0;
        orderInventories = getOrderInventories();
        for (OrderInventory orderInventory : orderInventories) {
            sum += orderInventory.getTotalOffPrice();
        }
        return sum;
    }

    @Transient
    @JsonView(Views.OrderList.class)
    public int getNumberOfProducts(){
        return this.orderInventories.size();
    }

}