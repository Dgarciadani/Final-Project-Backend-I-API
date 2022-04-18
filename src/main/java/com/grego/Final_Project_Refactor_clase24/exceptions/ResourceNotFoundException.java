package com.grego.Final_Project_Refactor_clase24.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception{

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
