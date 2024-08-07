package com.mftplus.ecommerce.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApiException {

    private String field;

    private String message;

    private HttpStatus httpStatus;

    private ZonedDateTime time;

    private String jwt;

    public ApiException(String field, String message, HttpStatus httpStatus, ZonedDateTime time) {
        this.field = field;
        this.message = message;
        this.httpStatus = httpStatus;
        this.time = time;
    }
}
