package com.hingehealth.demo.exceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

@Component
@ResponseStatus(HttpStatus.CONFLICT)
public class DataAlreadyExistsException extends Exception {
    @Autowired
    public DataAlreadyExistsException(@Value("${exception.dataAlreadyExistsError}") String message) {
        super(message);
    }

}
