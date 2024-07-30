package com.mftplus.ecommerce.exception;

public class UserNotIdentified extends Exception{

    public UserNotIdentified(String message) {
        super(message);
    }

    public UserNotIdentified(String message, Throwable cause) {
        super(message, cause);
    }
}
