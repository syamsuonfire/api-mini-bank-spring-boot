package com.minibank.exception;


public class KamuKorupsiException extends RuntimeException {
    public KamuKorupsiException() {
        super();
    }

    public KamuKorupsiException(String message) {
        super(message);
    }

    public KamuKorupsiException(String message, Throwable cause) {
        super(message, cause);
    }
}
