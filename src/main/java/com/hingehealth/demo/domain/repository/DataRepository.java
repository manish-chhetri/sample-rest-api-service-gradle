package com.hingehealth.demo.domain.repository;

import com.hingehealth.demo.domain.entity.DataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataRepository extends JpaRepository<DataEntity, Integer> {

    List<DataEntity> findDataByParent(@Param("parent") int parent);

    DataEntity findDataByParentAndLabel(@Param("parent") int parent, @Param("label") String label);

}
