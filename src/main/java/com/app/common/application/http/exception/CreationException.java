package com.app.common.application.http.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CreationException extends RuntimeException {

    public CreationException() {
        super("entity could not be created");
    }

    public CreationException(String entity) {
        super(entity + " could not be created");
    }
}