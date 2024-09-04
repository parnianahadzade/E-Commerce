package com.mftplus.ecommerce.exception.component;

import com.mftplus.ecommerce.exception.dto.ApiResponse;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;

@Component
public class ApiValidationComponent {



    public ApiResponse handleValidationErrors(BindingResult bindingResult) {
        ApiResponse response = new ApiResponse();

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            response.setFieldErrors(errors);
            response.setSuccess(false);
        }

        return response;
    }
}
