package com.mftplus.ecommerce.exception.controllerAdvice;

import com.mftplus.ecommerce.exception.ApiException;
import com.mftplus.ecommerce.exception.ApiRequestException;
import com.mftplus.ecommerce.exception.DuplicateException;
import com.mftplus.ecommerce.exception.EmailFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;
@ControllerAdvice
public class ApiExceptionHandler {
    HttpStatus badRequest = HttpStatus.BAD_REQUEST;
    HttpStatus conflict = HttpStatus.CONFLICT;
    HttpStatus internalServerError = HttpStatus.INTERNAL_SERVER_ERROR;

    //if we are catching ApiRequestException
    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e){
        //1. create payload containing exception details

        ApiException apiException = new ApiException(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        //2. return response entity
        return new ResponseEntity<>(apiException, badRequest);

    }

    @ExceptionHandler(value = {DuplicateException.class})
    public ResponseEntity<Object> handleDuplicateException(DuplicateException e){

        ApiException apiException = new ApiException(
                e.getMessage(),
                conflict,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(apiException, conflict);

    }

    @ExceptionHandler(value = {EmailFailureException.class})
    public ResponseEntity<Object> handleEmailFailureException(EmailFailureException e){

        ApiException apiException = new ApiException(
                e.getMessage(),
                internalServerError,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(apiException, internalServerError);

    }
}
