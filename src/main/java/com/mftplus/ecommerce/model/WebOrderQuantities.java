package com.mftplus.ecommerce.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "web_order_quantities")
public class WebOrderQuantities {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "web_order_quantities_seq")
    @SequenceGenerator(name = "web_order_quantities_seq", sequenceName = "web_order_quantities_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private WebOrder order;

}