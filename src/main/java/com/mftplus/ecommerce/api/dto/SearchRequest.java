package com.mftplus.ecommerce.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchRequest {

    private int pageNumber;

    private String name;

    private Integer categoryId;

    private String brandName;

    private Integer minPrice;

    private Integer maxPrice;

    private boolean enableOff;

}
