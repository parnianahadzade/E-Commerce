package com.mftplus.ecommerce.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderSaveDTO {

    @NotBlank(message = "خالی نذارید")
    private OrderInventorySaveDTO inventory;

    @Min(value = 0, message = "تعداد سفارش باید برابر یا بیشتر از صفر باشد.")
    private Integer quantity;

}
