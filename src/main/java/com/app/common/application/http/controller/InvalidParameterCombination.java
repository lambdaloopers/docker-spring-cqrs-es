package com.app.common.application.http.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidParameterCombination extends RuntimeException {

    public InvalidParameterCombination() {
        super("Invalid combination of query parameters");
    }
}
