package com.mftplus.ecommerce.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    //this is for sending info to frontend

    private String jwt;

    private boolean succsess;

    private String failureReason;
}
