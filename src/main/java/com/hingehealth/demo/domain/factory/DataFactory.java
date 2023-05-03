package com.hingehealth.demo.domain.factory;

import com.hingehealth.demo.domain.entity.DataEntity;
import com.hingehealth.demo.domain.repository.DataRepository;
import com.hingehealth.demo.pojo.request.DataRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataFactory {
    @Autowired
    DataRepository dataRepository;

    public List<DataEntity> findDataByParent(Integer id) {
        List<DataEntity> dataEntity = dataRepository.findDataByParent(id);
        return dataEntity;
    }

    public DataEntity findDataByParentAndLabel(Integer id, String label) {
        DataEntity dataEntity = dataRepository.findDataByParentAndLabel(id, label);
        return dataEntity;
    }

    public DataEntity saveData(DataRequest treeDataRequest) {
        DataEntity dataEntity = new DataEntity();

        dataEntity.setParent(Integer.valueOf(treeDataRequest.getParent()));
        dataEntity.setLabel(treeDataRequest.getLabel());

        return dataRepository.save(dataEntity);
    }
}
