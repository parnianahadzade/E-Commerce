package com.mftplus.ecommerce.api.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductSaveDTO {
    @NotBlank(message = "لطفا این قسمت را خالی نگذازید.")
    @Pattern(regexp = "^[0-9]{1,25}$",message = "کد کالا نادرست است.")
    private String code;

    @NotBlank(message = "لطفا این قسمت را خالی نگذازید.")
    @Pattern(regexp = "^[A-Za-zآ-ی ]{3,}$",message = "نام کالا نادرست است.")
    private String productName;

    @NotBlank(message = "لطفا این قسمت را خالی نگذازید.")
    @Pattern(regexp = "^[.-A-Za-zآ-ی ]{10,}$",message = "توضیحات کالا نادرست است.")
    private String description;

    @Min(value = 1, message = "قیمت کالا باید بیشتر یا برابر یک باشد.")
    @NotNull(message = "لطفا این قسمت را خالی نگذازید.")
    private Integer price;

    @Min(value = 0, message = "درصد تخفیف کالا باید بیشتر یا برابر صفر باشد.")
    @Max(value = 99, message = "درصد تخفیف کالا باید کمتر  یا برابر نود و نه باشد.")
    @NotNull(message = "لطفا این قسمت را خالی نگذازید.")
    private Integer offPercent;

    @NotBlank(message = "لطفا این قسمت را خالی نگذازید.")
    @Pattern(regexp = "^[A-Za-zآ-ی ]{3,10}$",message = "جنس کالا نادرست است.")
    private String material;

    @NotBlank(message = "لطفا این قسمت را خالی نگذازید.")
    @Pattern(regexp = "^[A-Za-zآ-ی ]{3,10}$",message = "طرح کالا نادرست است.")
    private String pattern;

    @NotNull(message = "لطفا این قسمت را خالی نگذازید.")
    @Min(value = 1, message = "قد کالا باید بیشتر یا برابر یک باشد.")
    private Integer height;

    private Long categoryId;

    private Long brandId;

    private Long colorId;

    private List<InventorySaveDTO> inventories;

    private Long mainImageId;

    private List<Long> imageIds;



}
