package com.hingehealth.demo.services;

import com.hingehealth.demo.exceptions.DataAlreadyExistsException;
import com.hingehealth.demo.exceptions.MissingDataLabelException;
import com.hingehealth.demo.exceptions.MissingDataParentException;
import com.hingehealth.demo.pojo.request.DataRequest;
import com.hingehealth.demo.pojo.response.DataDetailResponse;
import com.hingehealth.demo.domain.entity.DataEntity;
import com.hingehealth.demo.domain.factory.DataFactory;
import com.hingehealth.demo.pojo.response.DataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;

@Service
public class DataService {

    @Autowired
    DataFactory dataFactory;

    public List<Map<String, DataDetailResponse>> getDataTree(int parent) {
        List<Map<String, DataDetailResponse>> dataResponseList = new ArrayList<Map<String, DataDetailResponse>>();

        List<DataEntity> dataEntities = dataFactory.findDataByParent(parent);

        dataEntities.forEach((dataEntity) -> {
            Map<String, DataDetailResponse> dataMap = new HashMap<String, DataDetailResponse>();

            DataDetailResponse dataDetailResponse = new DataDetailResponse();
            dataDetailResponse.setLabel(dataEntity.getLabel());

            // Recursively checking for Children node
            List<Map<String, DataDetailResponse>> treeDataChildrenResponseList
                    = getDataTree(dataEntity.getId());

            dataDetailResponse.setChildren(treeDataChildrenResponseList);

            dataMap.put(String.valueOf(dataEntity.getId()), dataDetailResponse);

            dataResponseList.add(dataMap);

        });

        return dataResponseList;
    }

    public DataResponse postTreeData(DataRequest dataRequest) {
        DataResponse dataResponse = new DataResponse();

        try {
            // Validating the request
            if (isNull(dataRequest.getLabel())) {
                throw new MissingDataLabelException("Data label is missing");
            } else if (isNull(dataRequest.getParent()) || "0".equals(dataRequest.getParent())) {
                throw new MissingDataParentException("Data parent is missing");
            }
            // Validating if data already exists
            DataEntity dataEntity
                    = dataFactory.findDataByParentAndLabel(
                            Integer.valueOf(dataRequest.getParent()), dataRequest.getLabel());
            if (!isNull(dataEntity)) {
                throw new DataAlreadyExistsException("Data already exists");
            }

            dataFactory.saveData(dataRequest);
            dataResponse.setMessage("Success");
        } catch (MissingDataLabelException missingDataLabelException) {
            dataResponse.setError(missingDataLabelException.getMessage());
            dataResponse.setStatus(HttpStatus.BAD_REQUEST);
        } catch (MissingDataParentException missingDataParentException) {
            dataResponse.setError(missingDataParentException.getMessage());
            dataResponse.setStatus(HttpStatus.BAD_REQUEST);
        } catch (DataAlreadyExistsException dataAlreadyExistsException) {
            dataResponse.setError(dataAlreadyExistsException.getMessage());
            dataResponse.setStatus(HttpStatus.CONFLICT);
        } catch (Exception e) {
            dataResponse.setError("Unknown exception occurred");
            dataResponse.setStatus(HttpStatus.BAD_REQUEST);
        }
        return dataResponse;
    }
}
