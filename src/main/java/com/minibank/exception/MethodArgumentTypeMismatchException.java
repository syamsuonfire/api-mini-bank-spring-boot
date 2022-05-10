package com.minibank.exception;


public class MethodArgumentTypeMismatchException extends RuntimeException {
    public MethodArgumentTypeMismatchException() {
        super();
    }

    public MethodArgumentTypeMismatchException(String message) {
        super(message);
    }

    public MethodArgumentTypeMismatchException(String message, Throwable cause) {
        super(message, cause);
    }
}
