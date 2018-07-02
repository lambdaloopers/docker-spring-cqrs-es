package com.app.common.application.http.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UpdateException extends RuntimeException {

    public UpdateException() {
        super("entity could not be updated");
    }

    public UpdateException(String entity) {
        super(entity + " could not be updated");
    }
}