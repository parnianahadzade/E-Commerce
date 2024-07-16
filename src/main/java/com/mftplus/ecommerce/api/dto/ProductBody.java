package com.mftplus.ecommerce.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductBody {
    @Pattern(regexp = "^[0-9]{5,50}$",message = "incorrect code!")
    private String code;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^[A-Za-z]{3,20}$",message = "incorrect name!")
    private String productName;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^[A-Za-z]{5,}$",message = "incorrect short description!")
    private String shortDescription;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^[A-Za-z]{10,}$",message = "incorrect long description!")
    private String longDescription;

    @Min(value = 1, message = "Price must be equal or greater than 1.")
    private Integer price;

    @Min(value = 0, message = "Off Percent must be equal or greater than 0.")
    private Integer offPercent;

    private String categoryName;

    private Long brandId;

    private Long colorId;

    private List<InventoryBody> inventoryBodies;

}
