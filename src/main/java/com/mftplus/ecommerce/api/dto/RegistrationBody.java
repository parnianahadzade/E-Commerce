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
public class RegistrationBody {
    @NotNull
    @NotBlank
    @Pattern(regexp = "^[A-Za-z-0-9]{2,50}$",message = "incorrect username !")
    private String username;

    @NotEmpty
    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{5,}$",message = "Minimum five characters, at least one letter and one number!")
    private String password;

    @NotEmpty
    @NotBlank
    private String confirmPassword;

    @NotNull
    @NotBlank
    @Email(message = "Incorrect Email Format!")
    private String email;

}
