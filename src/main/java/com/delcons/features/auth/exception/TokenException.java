package com.delcons.features.auth.exception;

public class TokenException extends RuntimeException {
    public TokenException() {
        super("Token is missing");
    }
    public TokenException(String message) {
        super(message);
    }
}
