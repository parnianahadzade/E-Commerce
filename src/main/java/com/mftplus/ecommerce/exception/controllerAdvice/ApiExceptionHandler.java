package com.mftplus.ecommerce.exception.controllerAdvice;

import com.mftplus.ecommerce.exception.dto.ApiExceptionResponse;
import com.mftplus.ecommerce.exception.*;
import com.mftplus.ecommerce.exception.dto.ApiException;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;

@ControllerAdvice
public class ApiExceptionHandler {
    HttpStatus badRequest = HttpStatus.BAD_REQUEST;
    HttpStatus conflict = HttpStatus.CONFLICT;
    HttpStatus internalServerError = HttpStatus.INTERNAL_SERVER_ERROR;
    HttpStatus notFound = HttpStatus.NOT_FOUND;
    HttpStatus notAcceptable = HttpStatus.NOT_ACCEPTABLE;

    HttpStatus noAccess = HttpStatus.FORBIDDEN;

    //if we are catching ApiRequestException
    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e){
        //1. create payload containing exception details

        ApiException apiException = new ApiException(
                null,
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
                null,
                e.getMessage(),
                conflict,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        ApiExceptionResponse apiExceptionResponse = new ApiExceptionResponse(Collections.singletonList(apiException));

        return new ResponseEntity<>(apiExceptionResponse, conflict);

    }

    @ExceptionHandler(value = {EmailFailureException.class})
    public ResponseEntity<Object> handleEmailFailureException(EmailFailureException e){

        ApiException apiException = new ApiException(
                null,
                e.getMessage(),
                internalServerError,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        ApiExceptionResponse apiExceptionResponse = new ApiExceptionResponse(Collections.singletonList(apiException));

        return new ResponseEntity<>(apiExceptionResponse, internalServerError);

    }

    @ExceptionHandler(value = {NoContentException.class})
    public ResponseEntity<Object> handleNoContentException(NoContentException e){

        ApiException apiException = new ApiException(
                null,
                e.getMessage(),
                notFound,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        ApiExceptionResponse apiExceptionResponse = new ApiExceptionResponse(Collections.singletonList(apiException));

        return new ResponseEntity<>(apiExceptionResponse, notFound);

    }

    @ExceptionHandler(value = {ValidationException.class})
    public ResponseEntity<Object> handleValidationException(ValidationException e){

        ApiException apiException = new ApiException(
                null,
                e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        ApiExceptionResponse apiExceptionResponse = new ApiExceptionResponse(Collections.singletonList(apiException));

        return new ResponseEntity<>(apiExceptionResponse, badRequest);

    }

    @ExceptionHandler(value = {UserIdentification.class})
    public ResponseEntity<Object> handleUserNotIdentified(UserIdentification e){

        ApiException apiException = new ApiException(
                null,
                e.getMessage(),
                notAcceptable,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        ApiExceptionResponse apiExceptionResponse = new ApiExceptionResponse(Collections.singletonList(apiException));

        return new ResponseEntity<>(apiExceptionResponse, notAcceptable);

    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException e){

        ApiException apiException = new ApiException(
                null,
                e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        ApiExceptionResponse apiExceptionResponse = new ApiExceptionResponse(Collections.singletonList(apiException));

        return new ResponseEntity<>(apiExceptionResponse, notAcceptable);

    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException e){

        ApiException apiException = new ApiException(
                null,
                e.getMessage(),
                HttpStatus.FORBIDDEN,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        ApiExceptionResponse apiExceptionResponse = new ApiExceptionResponse(Collections.singletonList(apiException));

        return new ResponseEntity<>(apiExceptionResponse, noAccess);

    }

    @ExceptionHandler(value = {UserAccessDeniedException.class})
    public ResponseEntity<Object> handleUserAccessDeniedException(UserAccessDeniedException e){

        ApiException apiException = new ApiException(
                null,
                e.getMessage(),
                HttpStatus.FORBIDDEN,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        ApiExceptionResponse apiExceptionResponse = new ApiExceptionResponse(Collections.singletonList(apiException));

        return new ResponseEntity<>(apiExceptionResponse, noAccess);

    }


}
