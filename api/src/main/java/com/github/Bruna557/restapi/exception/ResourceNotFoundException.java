package com.github.Bruna557.restapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception {

    /**
     * Instantiates a new Resource not found com.github.Bruna557.api.exception.
     *
     * @param message the message
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
