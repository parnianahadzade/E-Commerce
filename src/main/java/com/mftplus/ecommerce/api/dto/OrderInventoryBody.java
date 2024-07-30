package com.mftplus.ecommerce.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderInventoryBody {

    private Long id;

    private Integer quantity;

    private String size;
}
