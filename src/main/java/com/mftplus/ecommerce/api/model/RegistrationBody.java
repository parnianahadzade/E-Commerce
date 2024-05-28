package com.mftplus.ecommerce.api.model;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationBody {
    @NotNull
    @NotBlank
    @Size(min = 5,max = 15,message = "incorrect size")
    public String username;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{5,}$",message = "Minimum five characters, at least one letter and one number!")
    public String password;


    @NotNull
    @NotBlank
    @Email
    public String email;

    @NotNull
    @NotBlank
    public String firstName;

    @NotNull
    @NotBlank
    public String lastName;
}
