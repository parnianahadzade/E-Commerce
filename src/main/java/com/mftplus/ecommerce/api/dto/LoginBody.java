package com.mftplus.ecommerce.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class LoginBody {

    @NotNull
    @NotBlank
    private String username;

    @NotNull
    @NotBlank
    private String password;
}
