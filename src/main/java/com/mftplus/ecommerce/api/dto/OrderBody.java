package com.mftplus.ecommerce.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderBody {

    private OrderInventoryBody inventory;

    private Integer quantity;

}
