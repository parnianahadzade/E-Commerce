package com.mftplus.ecommerce.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductBody {

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


    private String categoryName;

    private Long brandId;

    private List<InventoryBody> inventoryBodies;

}
