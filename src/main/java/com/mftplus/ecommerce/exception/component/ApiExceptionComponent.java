package com.mftplus.ecommerce.exception.component;

import com.mftplus.ecommerce.exception.dto.ApiException;
import com.mftplus.ecommerce.exception.dto.ApiExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ApiExceptionComponent {

    public static ResponseEntity<ApiExceptionResponse> handleValidationErrors(BindingResult result) {

        if (result.hasErrors()) {
            List<ApiException> apiExceptions = result.getFieldErrors().stream()
                    .map(error -> new ApiException(error.getField(), error.getDefaultMessage(), HttpStatus.BAD_REQUEST, ZonedDateTime.now(ZoneId.of("Z")))
                    ).collect(Collectors.toList());

            ApiExceptionResponse apiExceptionResponse = new ApiExceptionResponse(apiExceptions);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiExceptionResponse);
        }

        return null; // or throw exception if desired
    }
}
