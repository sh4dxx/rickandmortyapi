package com.rickandmorty.api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ExceptionInternalServerError extends RuntimeException {
    private final HttpStatus httpStatus;

    public ExceptionInternalServerError(HttpStatus code, String message) {
        super(message);
        this.httpStatus = code;
    }

    public ExceptionInternalServerError(String message) {
        super(message);
        this.httpStatus = HttpStatus.NOT_FOUND;
    }
}
