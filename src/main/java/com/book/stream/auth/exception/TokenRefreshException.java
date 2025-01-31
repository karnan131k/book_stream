package com.book.stream.auth.exception;

import java.io.Serial;

public class TokenRefreshException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public TokenRefreshException(String token, String message) {
        super(String.format("Failed for [%s]: %s", token, message));
    }
}
