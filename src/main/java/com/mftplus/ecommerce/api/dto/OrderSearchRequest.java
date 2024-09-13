package com.mftplus.ecommerce.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderSearchRequest {

    private Integer pageNumber;

    private String orderStatus;

    private String firstName;

    private String lastName;

    private String trackingCode;
}
