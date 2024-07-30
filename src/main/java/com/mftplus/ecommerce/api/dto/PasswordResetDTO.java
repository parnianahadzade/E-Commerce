package com.mftplus.ecommerce.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
public class PasswordResetDTO {
    @NotBlank(message = "لطفا این قسمت را خالی نگذازید.")
    private String token;

    //new password
    @NotBlank(message = "لطفا این قسمت را خالی نگذازید.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{5,}$",message = "حداقل 5 حرف ، حداقل یک حرف و یک عدد.")
    private String password;
}
