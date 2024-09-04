package com.mftplus.ecommerce.exception.controllerAdvice;

import com.mftplus.ecommerce.exception.*;
import com.mftplus.ecommerce.exception.dto.ApiOverallError;
import com.mftplus.ecommerce.exception.dto.ApiResponse;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {
    HttpStatus badRequest = HttpStatus.BAD_REQUEST;
    HttpStatus conflict = HttpStatus.CONFLICT;
    HttpStatus internalServerError = HttpStatus.INTERNAL_SERVER_ERROR;
    HttpStatus notFound = HttpStatus.NOT_FOUND;
    HttpStatus notAcceptable = HttpStatus.NOT_ACCEPTABLE;

    HttpStatus noAccess = HttpStatus.FORBIDDEN;


    @ExceptionHandler(value = {DuplicateException.class})
    public ResponseEntity<Object> handleDuplicateException(DuplicateException e){

        ApiOverallError apiOverallError = new ApiOverallError(
                e.getMessage(),
                conflict,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        ApiResponse apiResponse = new ApiResponse(
                null,
                apiOverallError,
                false,
                null,
                null
        );

        return new ResponseEntity<>(apiResponse, conflict);

    }

    @ExceptionHandler(value = {EmailFailureException.class})
    public ResponseEntity<Object> handleEmailFailureException(EmailFailureException e){

        ApiOverallError apiOverallError = new ApiOverallError(
                e.getMessage(),
                internalServerError,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        ApiResponse apiResponse = new ApiResponse(
                null,
                apiOverallError,
                false,
                null,
                null
        );

        return new ResponseEntity<>(apiResponse, internalServerError);

    }

    @ExceptionHandler(value = {NoContentException.class})
    public ResponseEntity<Object> handleNoContentException(NoContentException e){

        ApiOverallError apiOverallError = new ApiOverallError(
                e.getMessage(),
                notFound,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        ApiResponse apiResponse = new ApiResponse(
                null,
                apiOverallError,
                false,
                null,
                null
        );

        return new ResponseEntity<>(apiResponse, notFound);

    }

    @ExceptionHandler(value = {ValidationException.class})
    public ResponseEntity<Object> handleValidationException(ValidationException e){

        ApiOverallError apiOverallError = new ApiOverallError(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        ApiResponse apiResponse = new ApiResponse(
                null,
                apiOverallError,
                false,
                null,
                null
        );

        return new ResponseEntity<>(apiResponse, badRequest);

    }

    @ExceptionHandler(value = {UserIdentification.class})
    public ResponseEntity<Object> handleUserNotIdentified(UserIdentification e){

        ApiOverallError apiOverallError = new ApiOverallError(
                e.getMessage(),
                notAcceptable,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        ApiResponse apiResponse = new ApiResponse(
                null,
                apiOverallError,
                false,
                null,
                null
        );

        return new ResponseEntity<>(apiResponse, notAcceptable);

    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException e){

        ApiOverallError apiOverallError = new ApiOverallError(
                e.getMessage(),
                notAcceptable,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        ApiResponse apiResponse = new ApiResponse(
                null,
                apiOverallError,
                false,
                null,
                null
        );

        return new ResponseEntity<>(apiResponse, notAcceptable);

    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException e){

        ApiOverallError apiOverallError = new ApiOverallError(
                e.getMessage(),
                noAccess,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        ApiResponse apiResponse = new ApiResponse(
                null,
                apiOverallError,
                false,
                null,
                null
        );

        return new ResponseEntity<>(apiResponse, noAccess);

    }

    @ExceptionHandler(value = {UserAccessDeniedException.class})
    public ResponseEntity<Object> handleUserAccessDeniedException(UserAccessDeniedException e){

        ApiOverallError apiOverallError = new ApiOverallError(
                e.getMessage(),
                noAccess,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        ApiResponse apiResponse = new ApiResponse(
                null,
                apiOverallError,
                false,
                null,
                null
        );

        return new ResponseEntity<>(apiResponse, noAccess);

    }

    @ExceptionHandler(value = {InvalidDataException.class})
    public ResponseEntity<Object> handleInvalidDataException(InvalidDataException e){

        ApiOverallError apiOverallError = new ApiOverallError(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        ApiResponse apiResponse = new ApiResponse(
                null,
                apiOverallError,
                false,
                null,
                null
        );

        return new ResponseEntity<>(apiResponse, badRequest);

    }

    @ExceptionHandler(value = {IOException.class})
    public ResponseEntity<Object> handleIOException(IOException e){

        ApiOverallError apiOverallError = new ApiOverallError(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        ApiResponse apiResponse = new ApiResponse(
                null,
                apiOverallError,
                false,
                null,
                null
        );

        return new ResponseEntity<>(apiResponse, badRequest);

    }


}
