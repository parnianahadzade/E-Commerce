package com.mftplus.ecommerce.api.dto;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderSaveDTO {

    private OrderInventorySaveDTO inventory;

    @Min(value = 0, message = "تعداد سفارش باید برابر یا بیشتر از صفر باشد.")
    private Integer quantity;

}
