package com.mftplus.ecommerce.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserIdentificationDTO {

    @NotBlank(message = "لطفا این قسمت را خالی نگذازید.")
    @Pattern(regexp = "^[A-Za-zآ-ی]{3,20}$",message = "نام نادرست است.")
    private String firstName;

    @NotBlank(message = "لطفا این قسمت را خالی نگذازید.")
    @Pattern(regexp = "^[A-Za-zآ-ی]{3,20}$",message = "نام خانوادگی نادرست است.")
    private String lastName;

    @NotBlank(message = "لطفا این قسمت را خالی نگذازید.")
    @Pattern(regexp = "^09\\d{9}$",message = "تلفن همراه نادرست است.")
    private String phoneNumber;

    @NotBlank(message = "لطفا این قسمت را خالی نگذازید.")
    @Pattern(regexp = "^[A-Za-zآ-ی]{10,50}$",message = "آدرس نادرست است.")
    private String addressLine;

    @NotBlank(message = "لطفا این قسمت را خالی نگذازید.")
    @Pattern(regexp = "^[0-9]{10}$",message = "کد پستی نادرست است.")
    private String postalCode;

}
