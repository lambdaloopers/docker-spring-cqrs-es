package com.app.common.application.http.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super("entity could not be found");
    }

    public NotFoundException(String entity) {
        super(entity + " could not be found");
    }
}