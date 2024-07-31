package com.mftplus.ecommerce.exception;

public class UserIdentification extends Exception{

    public UserIdentification(String message) {
        super(message);
    }

    public UserIdentification(String message, Throwable cause) {
        super(message, cause);
    }
}
