package com.mftplus.ecommerce.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class UserDataChange {

    @NotNull
    @NotBlank
    @Pattern(regexp = "^[A-Za-z-0-9]{2,50}$",message = "incorrect username !")
    private String username;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^[A-Za-z]{3,20}$",message = "incorrect first name!")
    private String firstName;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^[A-Za-z]{3,20}$",message = "incorrect last name!")
    private String lastName;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^[0-9]{11}$",message = "incorrect phone number!")
    private String phoneNumber;
}
