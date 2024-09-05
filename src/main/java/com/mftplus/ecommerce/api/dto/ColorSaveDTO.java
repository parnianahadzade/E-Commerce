package com.mftplus.ecommerce.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ColorSaveDTO {

    @NotBlank(message = "لطفا این قسمت را خالی نگذازید.")
    @Pattern(regexp = "^[A-Za-zآ-ی ]{3,20}$",message = "نام رنگ نادرست است.")
    private String name;

    @NotBlank(message = "لطفا این قسمت را خالی نگذازید.")
    @Pattern(regexp = "^#(?:[0-9a-fA-F]{3}){1,2}$",message = "کد رنگ نادرست است.")
    private String hexCode;
}
