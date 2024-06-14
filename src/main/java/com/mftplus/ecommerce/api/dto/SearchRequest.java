package com.mftplus.ecommerce.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SearchRequest {

    private String name;

    private List<String> categoryNames = new ArrayList<>();

    private String brandName;

//    private Long colorId;

//    private String price;

//    private String offPercent;
}
