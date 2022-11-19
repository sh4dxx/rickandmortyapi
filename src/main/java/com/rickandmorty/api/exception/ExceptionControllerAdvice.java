package com.rickandmorty.api.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(value = ExceptionNotFountHandler.class)
    public ResponseEntity<ExceptionResponse> runtimeExceptionHandler(ExceptionNotFountHandler ex){
        ExceptionResponse exception = ExceptionResponse.builder()
                .httpStatus(ex.getHttpStatus())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(exception, ex.getHttpStatus());
    }

    @ExceptionHandler(value = ExceptionBadRequestHandler.class)
    public ResponseEntity<ExceptionResponse> runtimeExceptionHandler(ExceptionBadRequestHandler ex){
        ExceptionResponse exception = ExceptionResponse.builder()
                .httpStatus(ex.getHttpStatus())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(exception, ex.getHttpStatus());
    }


    @ExceptionHandler(value = ExceptionInternalServerError.class)
    public ResponseEntity<ExceptionResponse> runtimeExceptionHandler(ExceptionInternalServerError ex){
        ExceptionResponse exception = ExceptionResponse.builder()
                .httpStatus(ex.getHttpStatus())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(exception, ex.getHttpStatus());
    }
}
