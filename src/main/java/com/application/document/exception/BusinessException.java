package com.application.document.exception;

/**
 * Created by Semih, 3.07.2023
 */
public class BusinessException extends RuntimeException {

    public BusinessException(Exception pCause) {
        super(pCause);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(String message) {
        super(message);
    }
}
