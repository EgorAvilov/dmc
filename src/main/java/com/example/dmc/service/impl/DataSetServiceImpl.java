package com.example.dmc.service.impl;

import com.example.dmc.entity.DataSet;
import com.example.dmc.repository.DataSetRepository;
import com.example.dmc.service.DataSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataSetServiceImpl implements DataSetService {
    private final DataSetRepository dataSetRepository;

    @Autowired
    public DataSetServiceImpl(DataSetRepository dataSetRepository) {
        this.dataSetRepository = dataSetRepository;
    }

    @Override
    public DataSet create(DataSet dataSet) {
        return dataSetRepository.save(dataSet);
    }
}
