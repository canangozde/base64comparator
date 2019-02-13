package com.example.base64comparator.base64comparator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * LeftOrRightDataNotFoundException for nonexisting left or right data.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class LeftOrRightDataNotFoundException extends RuntimeException {

    /**
     * Constructor for LeftOrRightDataNotFoundException class.
     */
    public LeftOrRightDataNotFoundException() {

        super("Left or right data is null");
    }
}
