package com.rickandmorty.api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ExceptionBadRequestHandler extends RuntimeException {
    private final HttpStatus httpStatus;

    public ExceptionBadRequestHandler(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public ExceptionBadRequestHandler(String message) {
        super(message);
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
