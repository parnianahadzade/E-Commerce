package com.mftplus.ecommerce.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrandSaveDTO {

    @NotBlank(message = "لطفا این قسمت را خالی نگذازید.")
    @Pattern(regexp = "^[A-Za-zآ-ی ]{3,20}$",message = "نام برند نادرست است.")
    private String name;

    @NotBlank(message = "لطفا این قسمت را خالی نگذازید.")
    @Pattern(regexp = "^[.-A-Za-zآ-ی ]{3,100}$",message = "توضیحات برند نادرست است.")
    private String explanation;
}
