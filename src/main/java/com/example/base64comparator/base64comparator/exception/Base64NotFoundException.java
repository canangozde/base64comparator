package com.example.base64comparator.base64comparator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Base64NotFoundException for nonexisting id.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class Base64NotFoundException extends RuntimeException {

    /**
     * Constructor for Base64NotFoundException class.
     */
    public Base64NotFoundException() {
        super("No base64 file found to compare with given id");
    }
}
