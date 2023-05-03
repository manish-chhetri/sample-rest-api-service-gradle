package com.hingehealth.demo.utils;

import com.hingehealth.demo.pojo.response.DataDetailResponse;
import com.hingehealth.demo.pojo.response.DataResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ApiResponse<T> {
    public ResponseEntity<?> send(List<Map<String, DataDetailResponse>> response) {
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> send(DataResponse response) {
        if (response.getError() != null) {

            return error((T) response, response.getStatus());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> error(T errorPayload, HttpStatus status) {
        return new ResponseEntity<>(errorPayload, status);
    }

}
