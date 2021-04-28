package com.usfbank.app.exception;

public class AccountException extends Exception {

    public AccountException() {
        super();
    }

    public AccountException(final String message) {
        super(message);
    }
}