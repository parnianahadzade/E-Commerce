package com.mftplus.ecommerce.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchRequest {

    private int pageNumber;

    private String name;

    private Integer categoryId;

    private String brandName;

//    private Long colorId;

    private Integer minPrice;

    private Integer maxPrice;

    private boolean enableOff;

//    private Integer minOffPercent;
//
//    private Integer maxOffPercent;
}
