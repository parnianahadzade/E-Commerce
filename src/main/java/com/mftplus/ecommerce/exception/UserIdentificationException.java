package com.mftplus.ecommerce.exception;

public class UserIdentificationException extends Exception{

    public UserIdentificationException(String message) {
        super(message);
    }

    public UserIdentificationException(String message, Throwable cause) {
        super(message, cause);
    }
}
