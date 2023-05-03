package com.hingehealth.demo.controllers;

import com.hingehealth.demo.pojo.request.DataRequest;
import com.hingehealth.demo.pojo.response.DataDetailResponse;
import com.hingehealth.demo.services.DataService;
import com.hingehealth.demo.pojo.response.DataResponse;
import com.hingehealth.demo.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class DataController {

    @Autowired
    DataService dataService;

    @Autowired
    ApiResponse apiResponse;

    @GetMapping("${controller.path.getTree}")
    public ResponseEntity<?> getDataTree() {

        List<Map<String, DataDetailResponse>> dataTreeResponseList = dataService.getDataTree(0);
        return apiResponse.send(dataTreeResponseList);
    }

    @PostMapping("${controller.path.postTree}")
    public ResponseEntity<?> postTreeData(@RequestBody DataRequest dataRequest) {

        DataResponse response = dataService.postTreeData(dataRequest);
        return apiResponse.send(response);
    }

}
