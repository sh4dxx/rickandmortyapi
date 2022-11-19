package com.rickandmorty.api.exception;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ExceptionResponse {

    private HttpStatus httpStatus;
    private String message;
    //private Throwable throwable;
}
