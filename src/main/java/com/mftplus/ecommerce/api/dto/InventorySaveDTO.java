package com.mftplus.ecommerce.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventorySaveDTO {

    @NotBlank(message = "لطفا این قسمت را خالی نگذازید.")
    private String size;

    @Min(value = 0, message = "تعداد کالا باید برابر یا بیشتر از صفر باشد.")
    private Integer quantity;

}
