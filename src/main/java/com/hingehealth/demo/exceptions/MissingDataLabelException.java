package com.hingehealth.demo.exceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

@Component
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MissingDataLabelException extends Exception {

    @Autowired
    public MissingDataLabelException(@Value("${exception.missingDataLabelError}") String message) {
        super(message);
    }
}
