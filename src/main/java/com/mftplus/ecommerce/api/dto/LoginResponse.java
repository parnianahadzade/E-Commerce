package com.mftplus.ecommerce.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    //this is for sending info to frontend

    private String jwt;

    private boolean success;

    private String failureReason;
}
