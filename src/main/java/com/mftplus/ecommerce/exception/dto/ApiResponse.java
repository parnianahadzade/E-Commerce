package com.mftplus.ecommerce.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {

    private Map<String, String> fieldErrors;

    private ApiOverallError overallError;

    private boolean success;

    private String successMessage;

    private Object data;
}
