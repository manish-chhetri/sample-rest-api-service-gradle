package com.hingehealth.demo.pojo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@JsonInclude(Include.NON_NULL)
public class DataResponse {
    public String message;
    public String error;
    public HttpStatus status;
}
