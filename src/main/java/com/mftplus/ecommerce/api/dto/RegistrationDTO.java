package com.mftplus.ecommerce.api.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDTO {
    @NotBlank(message = "لطفا این قسمت را خالی نگذازید.")
    @Pattern(regexp = "^[A-Za-z-0-9]{5,50}$",message = "نام کاربری نادرست است.")
    private String username;

    @NotBlank(message = "لطفا این قسمت را خالی نگذازید.")
//    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{5,}$",message = "حداقل 5 حرف ، حداقل یک حرف و یک عدد.")
    private String password;

    @NotBlank(message = "لطفا این قسمت را خالی نگذازید.")
    private String confirmPassword;

    @Email(message = "ایمیل نادرست است.")
    @NotBlank(message = "لطفا این قسمت را خالی نگذازید.")
    private String email;

//    @AssertTrue(message = "Passwords do not match")
//    public boolean isPasswordsMatch() {
//        return password != null && password.equals(confirmPassword);
//    }

}
