package com.rickandmorty.api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ExceptionNotFountHandler extends RuntimeException {
    private final HttpStatus httpStatus;

    public ExceptionNotFountHandler(HttpStatus code, String message) {
        super(message);
        this.httpStatus = code;
    }

    public ExceptionNotFountHandler(String message) {
        super(message);
        this.httpStatus = HttpStatus.NOT_FOUND;
    }
}
