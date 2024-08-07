package com.mftplus.ecommerce.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {

    @NotBlank(message = "لطفا این قسمت را خالی نگذازید.")
    @Pattern(regexp = "^[A-Za-z-0-9]{5,50}$",message = "نام کاربری نادرست است.")
    private String username;

    @NotBlank(message = "لطفا این قسمت را خالی نگذازید.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{5,}$",message = "حداقل 5 حرف ، حداقل یک حرف و یک عدد.")
    private String password;
}
