package com.application.document.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by Semih, 3.07.2023
 */
public class AuthException extends AuthenticationException {

    public AuthException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public AuthException(String msg) {
        super(msg);
    }
}
