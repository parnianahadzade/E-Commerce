package com.mftplus.ecommerce.exception.controllerAdvice;

import com.mftplus.ecommerce.exception.*;
import jakarta.validation.ValidationException;
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
    HttpStatus notFound = HttpStatus.NOT_FOUND;
    HttpStatus notAcceptable = HttpStatus.NOT_ACCEPTABLE;

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

    @ExceptionHandler(value = {NoContentException.class})
    public ResponseEntity<Object> handleNoContentException(NoContentException e){

        ApiException apiException = new ApiException(
                e.getMessage(),
                notFound,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(apiException, notFound);

    }

    @ExceptionHandler(value = {ValidationException.class})
    public ResponseEntity<Object> handleValidationException(ValidationException e){

        ApiException apiException = new ApiException(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(apiException, badRequest);

    }

    @ExceptionHandler(value = {UserIdentification.class})
    public ResponseEntity<Object> handleUserNotIdentified(UserIdentification e){

        ApiException apiException = new ApiException(
                e.getMessage(),
                notAcceptable,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(apiException, notAcceptable);

    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException e){

        ApiException apiException = new ApiException(
                e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(apiException, notAcceptable);

    }
}
