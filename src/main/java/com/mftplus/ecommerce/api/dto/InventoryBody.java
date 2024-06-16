package com.mftplus.ecommerce.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryBody {

    private Long colorId;

    private Integer quantity;

    private Integer price;

    private Integer offPercent;
}
