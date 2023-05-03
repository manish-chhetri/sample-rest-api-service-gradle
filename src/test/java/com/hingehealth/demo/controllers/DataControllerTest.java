package com.hingehealth.demo.controllers;

import com.hingehealth.demo.pojo.request.DataRequest;
import com.hingehealth.demo.pojo.response.DataResponse;
import com.hingehealth.demo.services.DataService;
import com.hingehealth.demo.utils.ApiResponse;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DataController.class)
public class DataControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DataService dataService;

    @MockBean
    private ApiResponse apiResponse;

    @Test
    public void testGetDataTree() throws Exception {
        mockMvc.perform(get("/api/tree").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void testPostDataTree() throws Exception {
        DataResponse dataResponse = mock(DataResponse.class);
        JSONObject requestJson = new JSONObject()
                .put("parent", "7")
                .put("label", "Peacock");
        DataRequest dataRequest = mock(DataRequest.class);
        when(dataService.postTreeData(dataRequest)).thenReturn(dataResponse);
        mockMvc.perform(post("/api/tree")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(requestJson))
                )
                .andExpect(status().isOk());
    }
}
