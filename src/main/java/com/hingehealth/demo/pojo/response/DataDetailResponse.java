package com.hingehealth.demo.pojo.response;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class DataDetailResponse {

    public String label;
    public List<Map<String, DataDetailResponse>> children;
}
