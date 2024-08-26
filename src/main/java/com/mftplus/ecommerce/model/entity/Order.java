package com.mftplus.ecommerce.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
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
@ToString
@Entity(name = "orderEntity")
@Table(name = "order_tbl")
public class Order extends Base{
    //invoice

    @JsonView(Views.OrderList.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
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

    @JsonView(Views.OrderList.class)
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "order_status")
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order")
    private List<OrderInventory> orderInventories = new ArrayList<>();

    @JsonView(Views.OrderList.class)
    @Transient
    public Integer getTotalOrderPrice(){
        Integer sum = 0;
        orderInventories = getOrderInventories();
        for (OrderInventory orderInventory : orderInventories) {
            sum += orderInventory.getTotalPrice();
        }
        return sum;
    }

    @JsonView(Views.OrderList.class)
    @Transient
    public int getNumberOfProducts(){
        return this.orderInventories.size();
    }

}