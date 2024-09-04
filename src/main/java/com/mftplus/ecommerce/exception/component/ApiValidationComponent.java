package com.mftplus.ecommerce.exception.component;

import com.mftplus.ecommerce.exception.dto.ApiFieldError;
import com.mftplus.ecommerce.exception.dto.ApiResponse;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

@Component
public class ApiValidationComponent {


    public ApiResponse handleValidationErrors(BindingResult bindingResult) {
        ApiResponse response = new ApiResponse();

        List<ApiFieldError> fieldErrors = new ArrayList<>();

        if (bindingResult.hasErrors()) {
            for (FieldError error : bindingResult.getFieldErrors()) {
                fieldErrors.add(new ApiFieldError(error.getField(), error.getDefaultMessage()));
            }
        }

        response.setFieldErrors(fieldErrors);

        if (!fieldErrors.isEmpty()) {
            response.setSuccess(false);
        }

        return response;
    }
}
