package com.mftplus.ecommerce.exception;

public class NoContentException extends Exception{

    public NoContentException(String message) {
        super(message);
    }

    public NoContentException(String message, Throwable cause) {
        super(message, cause);
    }
}
