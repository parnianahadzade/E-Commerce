package com.mftplus.ecommerce.api.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductBody {
    @Pattern(regexp = "^[0-9]{1,50}$",message = "کد کالا اشتباه وارد شده است!")
    private String code;

    @NotEmpty
    @NotBlank
    @Pattern(regexp = "^[A-Za-zآ-ی]{3,20}$",message = "نام کالا اشتباه وارد شده است!")
    private String productName;

    @NotEmpty
    @NotBlank
    @Pattern(regexp = "^[A-Za-zآ-ی]{10,}$",message = "توضیحات کالا اشتباه وارد شده است!")
    private String description;

    @Min(value = 1, message = "قیمت کالا باید بیشتر یا برابر یک باشد!")
    private Integer price;

    @Min(value = 0, message = "درصد تخفیف کالا باید بیشتر یا برابر صفر باشد!")
    private Integer offPercent;

    @Pattern(regexp = "^[A-Za-zآ-ی]{3,20}$",message = "جنس کالا اشتباه وارد شده است!")
    private String material;

    @Pattern(regexp = "^[A-Za-zآ-ی]{3,20}$",message = "طرح کالا اشتباه وارد شده است!")
    private String pattern;

    @Pattern(regexp = "^[A-Za-zآ-ی]{3,20}$",message = "قد کلا اشتباه وارد شده است!")
    private String height;

    private String categoryName;

    private Long brandId;

    private Long colorId;

    private List<InventoryBody> inventoryBodies;

}
