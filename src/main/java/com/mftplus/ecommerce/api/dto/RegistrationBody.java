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
    @Pattern(regexp = "^[a-z]{2,15}$",message = "incorrect username !")
    public String username;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{5,}$",message = "Minimum five characters, at least one letter and one number!")
    public String password;


    @NotNull
    @NotBlank
    @Email(message = "Incorrect Email Format!")
    public String email;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^[a-z]{3,10}$",message = "incorrect first name!")
    public String firstName;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^[a-z]{3,10}$",message = "incorrect last name!")
    public String lastName;
}
